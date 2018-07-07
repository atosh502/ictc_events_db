package com.sankalpa.ictc_events_database.controller;

import com.sankalpa.ictc_events_database.models.DateString;
import com.sankalpa.ictc_events_database.models.EventSection;
import com.sankalpa.ictc_events_database.service.EventSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventSectionController {

    @Autowired
    private EventSectionService eventSectionService;

    @GetMapping("/eventSections")
    public @ResponseBody List<EventSection> getAllEventSections(){
        return eventSectionService.getAllEventSections();
    }

    @GetMapping("/eventSections/{eventSectionId}")
    public @ResponseBody EventSection getEventSection(@PathVariable Long eventSectionId){
        return eventSectionService.getEventSection(eventSectionId);
    }

    @PostMapping("/eventSections")
    public void addEventSection(@RequestBody EventSection newEventSection){
        eventSectionService.addEventSection(newEventSection);
    }

    @PutMapping("/eventSections/{eventSectionId}")
    public void updateEventSection(@PathVariable Long eventSectionId, @RequestBody EventSection updatedEventSection){
        eventSectionService.updateEventSection(eventSectionId, updatedEventSection);
    }

    @DeleteMapping("/eventSections/{eventSectionId}")
    public void deleteEventSection(@PathVariable Long eventSectionId){
        eventSectionService.deleteEvenSection(eventSectionId);
    }

    @PostMapping("/eventSections/findByDate")
    public @ResponseBody boolean[][] returnTimeByRoomMatrix(@RequestBody DateString dateString){
        return eventSectionService.returnTimeByRoomMatrix(dateString);
    }
}
