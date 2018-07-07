package com.sankalpa.ictc_events_database.service;

import com.sankalpa.ictc_events_database.models.Admin;
import com.sankalpa.ictc_events_database.models.LoginRequest;
import com.sankalpa.ictc_events_database.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    public void updateAdmin(Admin updatedAdmin, Long adminId){
        adminRepository.update(updatedAdmin, adminId);
    }

    public void addAdmin(Admin newAdmin){
        adminRepository.save(newAdmin);
    }

    public void deleteAdmin(Long adminId){
        adminRepository.deleteById(adminId);
    }

    public Admin getAdmin(Long adminId){
        return adminRepository.findById(adminId);
    }

    public Long login(LoginRequest loginRequest){
        List<Admin> admins = adminRepository.login(loginRequest);
        if (admins.size() == 0){
            return Long.valueOf(-1);
        } else {
            return admins.get(0).getUserId();
        }
    }

    public void approveEvent(Long eventId, Long adminId){
        adminRepository.approveEvent(eventId, adminId);
    }

    public int rejectEvent(Long eventId, Long adminId) {
        return adminRepository.rejectEvent(eventId, adminId);
    }
}
