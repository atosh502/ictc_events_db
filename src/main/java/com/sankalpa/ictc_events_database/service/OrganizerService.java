package com.sankalpa.ictc_events_database.service;

import com.sankalpa.ictc_events_database.models.*;
import com.sankalpa.ictc_events_database.repository.OrganizerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private EventSectionService eventSectionService;
    @Autowired
    private RoomService roomService;

    private Logger log = LoggerFactory.getLogger(OrganizerService.class);

    public List<Organizer> getAllOrganizers(){
        return organizerRepository.findAll();
    }

    public int updateOrganizer(Organizer updatedOrganizer, Long organizerId){
        return organizerRepository.update(updatedOrganizer, organizerId);
    }

    public int addOrganizer(Organizer newOrganizer){
        return organizerRepository.save(newOrganizer);
    }

    public int deleteOrganizer(Long organizerId){
        return organizerRepository.deleteById(organizerId);
    }

    public Organizer getOrganizer(Long organizerId){
        return organizerRepository.findById(organizerId);
    }

    public Long login(LoginRequest loginRequest){
        List<Organizer> organizers = organizerRepository.login(loginRequest);
        if (organizers.size() == 0){
            return Long.valueOf(-1);
        } else {
            return organizers.get(0).getUserId();
        }
    }

    public List<Event> getAllEventsFilterByOrganizerId(Long organizerId) {
        return organizerRepository.getAllEventsFilterByOrganizerId(organizerId);
    }

    public void registerEvent(EventInfo eventInfo, Long organizerId){
        Long eventId = organizerRepository.addEvent(eventInfo.getEvent(), organizerId);

        if (eventId != -1) {
            organizerRepository.assignEventToOrganizer(eventId, organizerId);
        }

        eventInfo(eventInfo, organizerId, eventId);
    }

    public void eventInfo(EventInfo eventInfo, Long organizerId, Long eventId) {

        Event event = eventService.getEvent(eventId);

        List<RoomMatrix> roomMatrixList = eventInfo.getRoomMatrixList();

        event.setEventStartDate(roomMatrixList.get(0).getDate().toString());
        event.setEventEndDate(roomMatrixList.get(roomMatrixList.size() - 1).getDate().toString());

        eventService.updateEvent(eventId, event);

        for (RoomMatrix roomMatrix : roomMatrixList){
            LocalDate date = roomMatrix.getDate();

            boolean[][] matrix = roomMatrix.getMatrix();
            for (int i = 0; i < matrix.length; i++){
                if (atLeastOne(matrix[i]) == true){

                    // construct the eventSection
                    Long eventSectionId = eventSectionService.registerEventSection(new EventSection(), eventId);

                    EventSection eventSection = eventSectionService.getEventSection(eventSectionId);

                    eventSection.setEventSectionDate(date.toString());
                    eventSection.setEventSectionTime(LocalTime.of(9 + i, 0, 0).toString());

                    // update the database
                    eventSectionService.updateEventSection(eventSectionId, eventSection);

                    List<Room> rooms = roomService.getAllRoomsInOrder();
                    // rooms have id from 1 to 15

                    int ROOMS = rooms.size();

                    for (int j = 0; j < ROOMS; j++){
                        if(matrix[i][j] == true){
                            Room room = roomService.getRoom((long) 1 + j);
                            eventSectionService.assignEventSectionToRoom(eventSectionId, room.getRoomId());
                        }
                    }
                }
            }

        }
    }

    private boolean atLeastOne(boolean[] mat){
        // check if any of the time intervals is assigned a room
        // if true at least one room is assigned to that time intervals
        // so we need an eventSection for that.

        boolean result = false;
        for (int i = 0; i < mat.length; i++){
            result = result || mat[i];
        }
        return result;
    }
}
