package com.sankalpa.ictc_events_database.repository;

import com.sankalpa.ictc_events_database.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Event findById(Long eventId){
        return jdbcTemplate.queryForObject("select * from event where eventId=?",
                new Object[] { eventId },
                new EventRowMapper());
    }

    public List<Event> findAll() {
        return jdbcTemplate.query("select * from event", new EventRowMapper());
    }

    public int deleteById(Long eventId) {
        return jdbcTemplate.update("delete from event where eventId=?", new Object[] { eventId });
    }

    public int save(Event event) {
        return jdbcTemplate.update("insert into event(eventId, eventName, eventDescription, expectedNumberOfParticipants, " +
                        "eventDurationInDays, eventStartDate, eventEndDate) values(?,?,?,?,?,?,?)",
                new Object[] { event.getEventId(), event.getEventName(), event.getEventDescription(),
                            event.getExpectedNumberOfParticipants(), event.getEventDurationInDays(),
                            event.getEventStartDate(), event.getEventEndDate() });
    }

    public int update(Event event, Long eventId) {
        return jdbcTemplate.update("update event set eventName=?, eventDescription=?, expectedNumberOfParticipants=?, " +
                        "eventDurationInDays=?, eventStartDate=?, eventEndDate=? " +
                        "where eventId=?",
                new Object[] { event.getEventName(), event.getEventDescription(),
                        event.getExpectedNumberOfParticipants(), event.getEventDurationInDays(),
                        event.getEventStartDate(), event.getEventEndDate(), eventId });
    }

    public List<Event> getAllEventsFilterByAdminId(Long adminId){
        return jdbcTemplate.query("select * from event where eventId in (select eventId " +
                "from admin_approves_event where userId=?)", new Object[]{adminId}, new EventRowMapper());
    }
}
