package org.ahala.examples.theserver.services.implementations;

import lombok.RequiredArgsConstructor;
import org.ahala.examples.theserver.entities.Event;
import org.ahala.examples.theserver.repositories.EventsRepository;
import org.ahala.examples.theserver.services.EventManager;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class EventManagerImpl implements EventManager {

    private final EventsRepository repository;

    @Override
    public Event createEvent(Date when, String title, String description) {
        Event newEvent = Event.builder()
                .eventDate(when)
                .description(description)
                .title(title)
                .build();

        repository.save(newEvent);

        return newEvent;
    }

    @Override
    public Event[] findUpcomingEvents() {
        return repository.findNextEvents().toArray(Event[]::new);
    }

    @Override
    public Event findEvent(long id) {
        return repository.findById(id)
                .orElse(null);
    }
}
