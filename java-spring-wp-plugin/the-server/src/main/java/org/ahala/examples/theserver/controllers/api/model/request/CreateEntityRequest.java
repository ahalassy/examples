package org.ahala.examples.theserver.controllers.api.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Data
@NoArgsConstructor
public class CreateEntityRequest {

    private final static SimpleDateFormat formatter
            = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);

    private String title;
    private String description;
    private String when;

    public Date getEventDate() throws ParseException {
        return formatter.parse(when);

    }

}
