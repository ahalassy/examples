package org.ahala.examples.theserver.controllers.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.ahala.examples.theserver.entities.Event;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Data
@AllArgsConstructor
@Builder
public class EventResponse {

    private final static SimpleDateFormat formatter
            = new SimpleDateFormat("yyyy-MM-dd', 'HH:mm:ss", Locale.ENGLISH);

    public long id;
    public String when;
    public String description;
    public String title;

    public static EventResponse fromEntity(Event event) {
        return EventResponse.builder()
                .description(event.getDescription())
                .id(event.getId())
                .title(event.getTitle())
                .when(formatter.format(event.getEventDate()))
                .build();
    }

}
