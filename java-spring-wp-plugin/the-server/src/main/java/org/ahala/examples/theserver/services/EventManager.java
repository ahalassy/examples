package org.ahala.examples.theserver.services;

import org.ahala.examples.theserver.entities.Event;

import java.util.Date;

public interface EventManager {

    Event createEvent(Date when, String title, String description);

    Event[] findUpcomingEvents();

    Event findEvent(long id);

}
