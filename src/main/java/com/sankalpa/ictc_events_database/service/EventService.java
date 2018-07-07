package com.sankalpa.ictc_events_database.service;

import com.sankalpa.ictc_events_database.models.Event;
import com.sankalpa.ictc_events_database.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Event getEvent(Long eventId){
        return eventRepository.findById(eventId);
    }

    public int updateEvent(Long eventId, Event updatedEvent){
        return eventRepository.update(updatedEvent, eventId);
    }

    public int addEvent(Event newEvent){
        return eventRepository.save(newEvent);
    }

    public int deleteEvent(Long eventId){
        return eventRepository.deleteById(eventId);
    }

    public List<Event> getAllEventsFilterByAdminId(Long adminId){
        return eventRepository.getAllEventsFilterByAdminId(adminId);
    }
}
