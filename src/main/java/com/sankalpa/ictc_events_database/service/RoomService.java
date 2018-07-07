package com.sankalpa.ictc_events_database.service;

import com.sankalpa.ictc_events_database.models.Room;
import com.sankalpa.ictc_events_database.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getRoom(Long roomId){
        return roomRepository.findById(roomId);
    }

    public void addRoom(Room room){
        roomRepository.save(room);
    }

    public void updateRoom(Long roomId, Room updatedRoom){
        roomRepository.update(updatedRoom, roomId);
    }

    public void deleteRoom(Long roomId){
        roomRepository.deleteById(roomId);
    }

    public List<Room> getAllRoomsInOrder(){
        return roomRepository.findAllRoomsOrderByRoomId();
    }
}