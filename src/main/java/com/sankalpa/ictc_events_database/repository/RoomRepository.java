package com.sankalpa.ictc_events_database.repository;

import com.sankalpa.ictc_events_database.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Room findById(Long roomId){
        return jdbcTemplate.queryForObject("select * from room where roomId=?",
                new Object[]{roomId}, new RoomRowMapper());
    }

    public List<Room> findAll(){
        return jdbcTemplate.query("select * from room", new RoomRowMapper());
    }

    public int save(Room room){
        return jdbcTemplate.update("insert into room(roomName, roomCapacity, roomFloor, roomCategory, costPerHour," +
                "costPerDay, costPerUnit) values(?,?,?,?,?,?,?)", room.getRoomName(), room.getRoomCapacity(),
                room.getRoomFloor(), room.getRoomCategory(), room.getCostPerHour(), room.getCostPerDay(), room.getCostPerUnit());
    }

    public int update(Room room, Long roomId){
        return jdbcTemplate.update("update room set roomName=?, roomCapacity=?, roomFloor=?, roomCategory=?," +
                "costPerHour=?, costPerDay=?, costPerUnit=? where roomId=?", room.getRoomName(), room.getRoomCapacity(),
                room.getRoomFloor(), room.getRoomCategory(), room.getCostPerHour(), room.getCostPerDay(),
                room.getCostPerUnit(), roomId);
    }

    public int deleteById(Long roomId){
        return jdbcTemplate.update("delete from room where roomId=?", roomId);
    }

    public List<Room> findAllRoomsOrderByRoomId(){
        return jdbcTemplate.query("select * from room order by roomId", new RoomRowMapper());
    }
}
