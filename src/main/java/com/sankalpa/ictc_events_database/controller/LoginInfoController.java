package com.sankalpa.ictc_events_database.controller;

import com.sankalpa.ictc_events_database.models.LoginInfo;
import com.sankalpa.ictc_events_database.models.LoginRequest;
import com.sankalpa.ictc_events_database.models.Organizer;
import com.sankalpa.ictc_events_database.service.LoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginInfoController {

    @Autowired
    private LoginInfoService loginInfoService;

    @PostMapping("/login/admin")
    public @ResponseBody LoginInfo adminLogin(@RequestBody LoginRequest admin){
        return loginInfoService.adminLogin(admin);
    }

    @PostMapping("/login/organizer")
    public @ResponseBody LoginInfo organizerLogin(@RequestBody LoginRequest organizer){
        return loginInfoService.organizerLogin(organizer);
    }
}
