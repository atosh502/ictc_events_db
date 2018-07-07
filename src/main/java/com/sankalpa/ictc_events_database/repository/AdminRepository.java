package com.sankalpa.ictc_events_database.repository;

import com.sankalpa.ictc_events_database.models.Admin;
import com.sankalpa.ictc_events_database.models.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Admin findById(Long adminId){
        return jdbcTemplate.queryForObject("select * from admin where userId=?",
                new Object[] { adminId },
                new AdminRowMapper());
    }

    public List<Admin> findAll() {
        return jdbcTemplate.query("select * from admin", new AdminRowMapper());
    }

    public int deleteById(Long adminId) {
        return jdbcTemplate.update("delete from admin where userId=?", new Object[] { adminId });
    }

    public int save(Admin admin) {
        return jdbcTemplate.update("insert into admin(userId, userName, userPassword) values(?,?,?)",
                new Object[] { admin.getUserId(), admin.getUserName(), admin.getUserPassword() });
    }

    public int update(Admin admin, Long adminId) {
        return jdbcTemplate.update("update admin set userName=?, userPassword=? where userId=?",
                new Object[] { admin.getUserName(), admin.getUserPassword(), adminId });
    }

    public List<Admin> login(LoginRequest loginRequest) {
        return jdbcTemplate.query("select * from admin where userName=? and userPassword=?",
                new Object[]{loginRequest.getUserName(), loginRequest.getUserPassword()},
                new AdminRowMapper());
    }

    public void approveEvent(Long eventId, Long adminId){
        jdbcTemplate.update("insert into admin_approves_event(userId, eventId) values(?,?)",
                adminId, eventId);
        jdbcTemplate.update("update event set accepted=1 where eventId=?", eventId);
    }

    public int rejectEvent(Long eventId, Long adminId) {
        return jdbcTemplate.update("delete from event where eventId=?", eventId);
    }
}
