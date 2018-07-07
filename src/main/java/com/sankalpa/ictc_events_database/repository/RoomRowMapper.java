package com.sankalpa.ictc_events_database.repository;

import com.sankalpa.ictc_events_database.models.Room;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRowMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {

        Room room = new Room();
        room.setRoomId(resultSet.getLong("roomId"));
        room.setRoomName(resultSet.getString("roomName"));
        room.setRoomCategory(resultSet.getString("roomCategory"));
        room.setRoomCapacity(resultSet.getInt("roomCapacity"));
        room.setRoomFloor(resultSet.getInt("roomFloor"));
        room.setCostPerDay(resultSet.getInt("costPerDay"));
        room.setCostPerHour(resultSet.getInt("costPerHour"));
        room.setCostPerUnit(resultSet.getInt("costPerUnit"));
        return room;
    }
}
