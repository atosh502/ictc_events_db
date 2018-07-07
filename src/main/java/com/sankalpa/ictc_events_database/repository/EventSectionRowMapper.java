package com.sankalpa.ictc_events_database.repository;

import com.sankalpa.ictc_events_database.models.EventSection;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventSectionRowMapper implements RowMapper<EventSection> {

    @Override
    public EventSection mapRow(ResultSet resultSet, int i) throws SQLException {
        EventSection eventSection = new EventSection();
        eventSection.setEventSectionId(resultSet.getLong("eventSectionId"));
        eventSection.setEventSectionDate(resultSet.getString("eventSectionDate"));
        eventSection.setEventSectionTime(resultSet.getString("eventSectionTime"));
        return eventSection;
    }
}
