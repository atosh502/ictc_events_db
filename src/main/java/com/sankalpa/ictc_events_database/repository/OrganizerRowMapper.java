package com.sankalpa.ictc_events_database.repository;

import com.sankalpa.ictc_events_database.models.Organizer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizerRowMapper implements RowMapper<Organizer>{

    @Override
    public Organizer mapRow(ResultSet resultSet, int i) throws SQLException {

        Organizer organizer = new Organizer();
        organizer.setUserId(resultSet.getLong("userId"));
        organizer.setUserName(resultSet.getString("userName"));
        organizer.setUserPassword(resultSet.getString("userPassword"));
        organizer.setOrganizerName(resultSet.getString("organizerName"));
        organizer.setOrganizerAddress(resultSet.getString("organizerAddress"));
        organizer.setOrganizerEmail(resultSet.getString("organizerEmail"));
        organizer.setOrganizerPhone(resultSet.getString("organizerPhone"));
        return organizer;
    }
}
