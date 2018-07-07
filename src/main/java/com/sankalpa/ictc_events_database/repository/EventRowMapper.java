package com.sankalpa.ictc_events_database.repository;

import com.sankalpa.ictc_events_database.models.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRowMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet resultSet, int i) throws SQLException {
        Event event = new Event();
        event.setEventId(resultSet.getLong("eventId"));
        event.setAccepted(resultSet.getInt("accepted"));
        event.setEventDescription(resultSet.getString("eventDescription"));
        event.setEventName(resultSet.getString("eventName"));
        event.setEventDurationInDays(resultSet.getInt("eventDurationInDays"));
        event.setExpectedNumberOfParticipants(resultSet.getInt("expectedNumberOfParticipants"));
        event.setEventStartDate(resultSet.getString("eventStartDate"));
        event.setEventEndDate(resultSet.getString("eventEndDate"));
        return event;
    }
}
