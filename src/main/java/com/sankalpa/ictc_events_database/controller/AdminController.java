package com.sankalpa.ictc_events_database.controller;

import com.sankalpa.ictc_events_database.models.Admin;
import com.sankalpa.ictc_events_database.models.Event;
import com.sankalpa.ictc_events_database.service.AdminService;
import com.sankalpa.ictc_events_database.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private EventService eventService;

    @GetMapping("/admins")
    public @ResponseBody
    List<Admin> getAllAdmins(){
        return adminService.getAllAdmins();
    }

    @GetMapping("/admins/{adminId}")
    public @ResponseBody Admin getAdmin(@PathVariable Long adminId){
        return adminService.getAdmin(adminId);
    }

    @PostMapping("/admins")
    public void addAdmin(@RequestBody Admin newAdmin){
        adminService.addAdmin(newAdmin);
    }

    @PutMapping("/admins/{adminId}")
    public void updateAdmin(@RequestBody Admin updatedAdmin, @PathVariable Long adminId){
        adminService.updateAdmin(updatedAdmin, adminId);
    }

    @DeleteMapping("/admins/{adminId}")
    public void deleteAdmin(@PathVariable Long adminId){
        adminService.deleteAdmin(adminId);
    }

    @GetMapping("/admins/{adminId}/events")
    public List<Event> getAllEventsFilterByAdminId(@PathVariable Long adminId){
        return eventService.getAllEventsFilterByAdminId(adminId);
    }

    @PostMapping("/admins/{adminId}/events/{eventId}")
    public void approveEvent(@PathVariable Long adminId, @PathVariable Long eventId){
        adminService.approveEvent(eventId, adminId);
    }

    @DeleteMapping("/admins/{adminId}/events/{eventId}")
    public int rejectEvent(@PathVariable Long adminId, @PathVariable Long eventId){
        return adminService.rejectEvent(eventId, adminId);
    }
}
