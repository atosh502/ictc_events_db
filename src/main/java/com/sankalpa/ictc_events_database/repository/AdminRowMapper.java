package com.sankalpa.ictc_events_database.repository;

import com.sankalpa.ictc_events_database.models.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Admin admin = new Admin();
        admin.setUserId(resultSet.getLong("userId"));
        admin.setUserName(resultSet.getString("userName"));
        admin.setUserPassword(resultSet.getString("userPassword"));
        return admin;
    }
}