package com.sankalpa.ictc_events_database.service;

import com.sankalpa.ictc_events_database.models.LoginInfo;
import com.sankalpa.ictc_events_database.models.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginInfoService {

    @Autowired
    private AdminService adminService;
    @Autowired
    private OrganizerService organizerService;

    public LoginInfo adminLogin(LoginRequest admin) {
        Long adminId = adminService.login(admin);
        int responseCode = adminId == -1 ? 404 : 202;
        return new LoginInfo(adminId, admin.getUserName(), admin.getUserPassword(), "ADMIN", responseCode);
    }

    public LoginInfo organizerLogin(LoginRequest organizer){
        Long organizerId = organizerService.login(organizer);
        int responseCode = organizerId == -1 ? 404 : 202;
        return new LoginInfo(organizerId, organizer.getUserName(), organizer.getUserPassword(), "ORG", responseCode);
    }
}
