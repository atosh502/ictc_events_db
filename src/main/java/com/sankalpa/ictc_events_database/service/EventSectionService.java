package com.sankalpa.ictc_events_database.service;

import com.sankalpa.ictc_events_database.models.DateString;
import com.sankalpa.ictc_events_database.models.EventSection;
import com.sankalpa.ictc_events_database.models.Room;
import com.sankalpa.ictc_events_database.repository.EventSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class EventSectionService {

    private static final int HOURS = 8;
    private static final int ROOMS = 15;

    @Autowired
    private EventSectionRepository eventSectionRepository;

    public List<EventSection> getAllEventSections(){
        return eventSectionRepository.findAll();
    }

    public EventSection getEventSection(Long eventSectionId){
        return eventSectionRepository.findById(eventSectionId);
    }

    public void updateEventSection(Long eventSectionId, EventSection updatedEvenSection){
        eventSectionRepository.update(updatedEvenSection, eventSectionId);
    }

    public void addEventSection(EventSection newEventSection){
        eventSectionRepository.save(newEventSection);
    }

    public void deleteEvenSection(Long eventSectionId){
        eventSectionRepository.deleteById(eventSectionId);
    }

    public boolean[][] returnTimeByRoomMatrix(DateString dateString){

        // TODO: make sure that eventSections are sorted by Time i.e. from 9 to 16

        LocalDate localDate = LocalDate.of(dateString.getYear(), dateString.getMonth(), dateString.getDay());
        Date date = Date.valueOf(localDate);

        List<EventSection> eventSections =  eventSectionRepository.findByEventSectionDate(date.toString());

        boolean[][] matrix = new boolean[HOURS][ROOMS];

        for (int i = 0; i < HOURS; i++){
            for (int j = 0; j < ROOMS; j++){
                matrix[i][j] = false;
            }
        }

        for (int i = 0; i < HOURS; i++){

            EventSection eventSection = eventSections.get(i);
            List<Room> rooms = eventSectionRepository.findRoomsByEventSection(eventSection.getEventSectionId());

            for (Room room : rooms){
                Long roomId = room.getRoomId();
                matrix[i][(int) (roomId - 1)] = true;
            }

        }

        return matrix;
    }

    // update the eventSection and event_contains_eventSection table;
    public Long registerEventSection(EventSection eventSection, Long eventId) {
        Long eventSectionId = eventSectionRepository.addEventSection(eventSection);
        eventSectionRepository.assignEventSectionToEvent(eventSectionId, eventId);
        return eventSectionId;
    }

    // update the eventSection_happensIn_room table;
    public void assignEventSectionToRoom(Long eventSectionId, Long roomId) {
        eventSectionRepository.assignEventSectionToRoom(eventSectionId, roomId);
    }
}
