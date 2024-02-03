package org.ahala.examples.theserver.components.implementations;

import org.ahala.examples.theserver.components.DateTime;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DefaultDateTimeProvider implements DateTime {
    @Override
    public Date now() {
        return new Date();
    }
}
