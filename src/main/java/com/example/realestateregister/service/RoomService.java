package com.example.realestateregister.service;

import com.example.realestateregister.dao.RoomJpaDao;
import com.example.realestateregister.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomJpaDao roomDao;

    @Autowired
    public RoomService(RoomJpaDao roomDao) {
        this.roomDao = roomDao;
    }

    public List<Room> listRooms() {
        return roomDao.findAll();
    }

    public Room getRoomById(long id) {
        return roomDao.findById(id).orElseThrow();
    }

    public long addRoomAndReturnId(Room room) {
        return roomDao.save(room).getId();
    }

    public void updateRoomById(Room newRoom, long id) {
        Room room = roomDao.findById(id).orElseThrow();
        room.setRoomType(newRoom.getRoomType());
        room.setSize(newRoom.getSize());
        room.setBuilding(newRoom.getBuilding());
        roomDao.save(room);
    }

    public void deleteRoomById(long id) {
        roomDao.deleteById(id);
    }
}
