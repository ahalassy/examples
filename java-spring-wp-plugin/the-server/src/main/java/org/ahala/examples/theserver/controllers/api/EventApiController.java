package org.ahala.examples.theserver.controllers.api;

import lombok.RequiredArgsConstructor;
import org.ahala.examples.theserver.controllers.api.model.request.CreateEntityRequest;
import org.ahala.examples.theserver.controllers.api.model.response.EventResponse;
import org.ahala.examples.theserver.entities.Event;
import org.ahala.examples.theserver.services.EventManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventApiController {

    private final EventManager eventManager;

    @GetMapping("/{id}")
    public ResponseEntity<?> findEvent(@PathVariable long id) {
        Event event = eventManager.findEvent(id);
        return event == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(event);
    }

    @GetMapping
    public ResponseEntity<?> findAllEvents() {
        EventResponse[] events = Arrays.stream(eventManager.findUpcomingEvents())
                .sorted(Comparator.comparing(Event::getEventDate))
                .map(EventResponse::fromEntity)
                .toArray(EventResponse[]::new);
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@ModelAttribute CreateEntityRequest request) {
        try {
            Event entity = eventManager.createEvent(
                    request.getEventDate(),
                    request.getTitle(),
                    request.getDescription()
            );

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(entity.getId())
                    .toUri();
            return ResponseEntity.created(uri).build();

        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
