package com.sankalpa.ictc_events_database.repository;

import com.sankalpa.ictc_events_database.models.Event;
import com.sankalpa.ictc_events_database.models.LoginRequest;
import com.sankalpa.ictc_events_database.models.Organizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Organizer findById(Long organizerId){
        return jdbcTemplate.queryForObject("select * from organizer where userId=?",
                new Object[] { organizerId },
                new OrganizerRowMapper());
    }

    public List<Organizer> findAll() {
        return jdbcTemplate.query("select * from organizer", new OrganizerRowMapper());
    }

    public int deleteById(Long organizerId) {
        return jdbcTemplate.update("delete from organizer where userId=?", new Object[] { organizerId });
    }

    public int save(Organizer organizer) {
        return jdbcTemplate.update("insert into organizer(userId, " +
                        "userName, " +
                        "userPassword, " +
                        "organizerName, " +
                        "organizerAddress, " +
                        "organizerEmail, " +
                        "organizerPhone) " +
                        "values(?,?,?,?,?,?,?)",
                new Object[] { organizer.getUserId(),
                        organizer.getUserName(),
                        organizer.getUserPassword(),
                        organizer.getOrganizerName(),
                        organizer.getOrganizerAddress(),
                        organizer.getOrganizerEmail(),
                        organizer.getOrganizerPhone() });
    }

    public int update(Organizer organizer, Long organizerId) {
        return jdbcTemplate.update("update organizer " +
                        "set userName=?, " +
                        "userPassword=?, " +
                        "organizerName=?, " +
                        "organizerAddress=?, " +
                        "organizerEmail=?, " +
                        "organizerPhone=? " +
                        "where userId=?",
                new Object[] { organizer.getUserName(), organizer.getUserPassword(), organizer.getOrganizerName(),
                        organizer.getOrganizerAddress(), organizer.getOrganizerEmail(), organizer.getOrganizerPhone(),
                        organizerId });
    }

    public List<Organizer> login(LoginRequest loginRequest) {
        return jdbcTemplate.query("select * from organizer where userName=? and userPassword=?",
                new Object[]{loginRequest.getUserName(), loginRequest.getUserPassword()},
                new OrganizerRowMapper());
    }

    public List<Event> getAllEventsFilterByOrganizerId(Long organizerId) {
        return jdbcTemplate.query("select * from event where eventId in " +
                "(select eventId from organizer_creates_event where userId=?)", new Object[]{organizerId},
                new EventRowMapper());
    }

    // adds an event to the organizer_creates_event table and returns the id of the newly created event;
    // TODO: analyze this function; maybe a new event gets added before I can query the event with max id;
    // TODO: discuss with UK & DB.

    // TODO: BLUNDER HERE!!!

    public Long addEvent(Event event, Long organizerId) {

        jdbcTemplate.update("insert into event(eventId, eventName, eventDescription, expectedNumberOfParticipants, " +
                        "eventDurationInDays, eventStartDate, eventEndDate) values(?,?,?,?,?,?,?)",
                new Object[] { event.getEventId(), event.getEventName(), event.getEventDescription(),
                        event.getExpectedNumberOfParticipants(), event.getEventDurationInDays(),
                        event.getEventStartDate(), event.getEventEndDate() });

        List<Event> events = jdbcTemplate.query("select * from event order by eventId desc", new EventRowMapper());
        if (events.size() > 0){
            return events.get(0).getEventId();
        } else {
            return Long.valueOf(-1);
        }
    }

    public void assignEventToOrganizer(Long eventId, Long organizerId) {
        // here the organizer_creates_event table is updated
        jdbcTemplate.update("insert into organizer_creates_event(userId, eventId) values(?,?)", organizerId, eventId);
    }
}