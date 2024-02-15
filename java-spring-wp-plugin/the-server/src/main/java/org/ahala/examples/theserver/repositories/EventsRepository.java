package org.ahala.examples.theserver.repositories;

import org.ahala.examples.theserver.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EventsRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.eventDate >= NOW()")
    List<Event> findNextEvents();
}
