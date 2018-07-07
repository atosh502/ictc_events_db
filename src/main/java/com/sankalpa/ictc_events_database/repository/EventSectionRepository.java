package com.sankalpa.ictc_events_database.repository;

import com.sankalpa.ictc_events_database.models.EventSection;
import com.sankalpa.ictc_events_database.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class EventSectionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<EventSection> findAll(){
        return jdbcTemplate.query("select * from eventSection", new EventSectionRowMapper());
    }

    public EventSection findById(Long eventSectionId){
        return jdbcTemplate.queryForObject("select * from eventSection where eventSectionId=?", new Object[]{eventSectionId},
                new EventSectionRowMapper());
    }

    public int save(EventSection eventSection){
        return jdbcTemplate.update("insert into eventSection(eventSectionId, eventSectionDate, eventSectionTime) values(?,?,?)",
                new Object[] { eventSection.getEventSectionId(), eventSection.getEventSectionDate(),
                        eventSection.getEventSectionTime() });
    }

    public int update(EventSection eventSection, Long eventSectionId){
        return jdbcTemplate.update("update eventSection set eventSectionDate=?, eventSectionTime=? where eventSectionId=?",
                new Object[] {eventSection.getEventSectionDate(), eventSection.getEventSectionTime(),
                        eventSectionId });
    }

    public int deleteById(Long eventSectionId){
        return jdbcTemplate.update("delete from eventSection where eventSectionId=?", new Object[]{eventSectionId});
    }

    public List<EventSection> findByEventSectionDate(String date) {
        return jdbcTemplate.query("select * from eventSection where eventSectionDate=? " +
                "order by eventSectionTime ", new Object[]{date}, new EventSectionRowMapper());
    }

    public List<Room> findRoomsByEventSection(Long eventSectionId){
        return jdbcTemplate.query("select * from room where roomId in " +
                "(select roomId from eventSection_happensIn_room where eventSectionId=?)",
                new Object[]{eventSectionId}, new RoomRowMapper());
    }

    public Long addEventSection(EventSection eventSection) {
        jdbcTemplate.update("insert into eventSection(eventSectionId, eventSectionDate, eventSectionTime) values(?,?,?)",
                new Object[]{eventSection.getEventSectionId(), eventSection.getEventSectionDate(),
                        eventSection.getEventSectionTime()});

        List<EventSection> eventSections = jdbcTemplate.query("select * from eventSection order by eventSectionId desc",
                new EventSectionRowMapper());
        if (eventSections.size() > 0){
            return eventSections.get(0).getEventSectionId();
        } else {
            return Long.valueOf(-1);
        }
    }

    public void assignEventSectionToEvent(Long eventSectionId, Long eventId) {
        jdbcTemplate.update("insert into event_contains_eventSection(eventId, eventSectionId) values(?,?)", new Object[]{eventId,
            eventSectionId});
    }

    public void assignEventSectionToRoom(Long eventSectionId, Long roomId) {
        jdbcTemplate.update("insert into eventSection_happensIn_room(eventSectionId, roomId) values(?,?)",
                eventSectionId, roomId);
    }
}
