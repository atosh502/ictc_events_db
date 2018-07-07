package com.sankalpa.ictc_events_database.controller;

import com.sankalpa.ictc_events_database.models.Event;
import com.sankalpa.ictc_events_database.models.EventInfo;
import com.sankalpa.ictc_events_database.models.Organizer;
import com.sankalpa.ictc_events_database.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizerController {

    @Autowired
    private OrganizerService organizerService;

    @GetMapping("/organizers")
    public @ResponseBody List<Organizer> getAllOrganizers(){
        return organizerService.getAllOrganizers();
    }

    @GetMapping("/organizers/{organizerId}")
    public @ResponseBody Organizer getOrganizer(@PathVariable Long organizerId){
        return organizerService.getOrganizer(organizerId);
    }

    @PostMapping("/organizers")
    public void addOrganizer(@RequestBody Organizer newOrganizer){
        organizerService.addOrganizer(newOrganizer);
    }

    @PutMapping("/organizers/{organizerId}")
    public void updateOrganizer(@RequestBody Organizer updateOrganizer, @PathVariable Long organizerId){
        organizerService.updateOrganizer(updateOrganizer, organizerId);
    }

    @DeleteMapping("/organizers/{organizerId}")
    public void deleteAdmin(@PathVariable Long organizerId){
        organizerService.deleteOrganizer(organizerId);
    }

    @GetMapping("/organizers/{organizerId}/events")
    public List<Event> getAllEventsFilterByOrganizerId(@PathVariable Long organizerId){
        return organizerService.getAllEventsFilterByOrganizerId(organizerId);
    }

    @PostMapping("/organizers/{organizerId}/eventInfo")
    public void registerEvent(@RequestBody EventInfo eventInfo, @PathVariable Long organizerId){
        organizerService.registerEvent(eventInfo, organizerId);
    }
}
