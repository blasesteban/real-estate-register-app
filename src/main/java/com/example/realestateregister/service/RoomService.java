package com.example.realestateregister.service;

import com.example.realestateregister.dao.RoomJpaDao;
import com.example.realestateregister.dto.RoomDto;
import com.example.realestateregister.exceptions.InvalidRoomTypeException;
import com.example.realestateregister.model.Room;
import com.example.realestateregister.model.RoomType;
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

    public long addRoomAndReturnId(RoomDto roomDto) throws InvalidRoomTypeException {
        Room room = new Room();
        try {
            room.setRoomType(RoomType.valueOf(roomDto.getRoomType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new InvalidRoomTypeException();
        }
        room.setSize(roomDto.getSize());
        return roomDao.save(room).getId();
    }

    public void updateRoomById(RoomDto roomDto, long id) throws InvalidRoomTypeException {
        Room room = roomDao.findById(id).orElseThrow();
        try {
            room.setRoomType(RoomType.valueOf(roomDto.getRoomType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new InvalidRoomTypeException();
        }
        room.setSize(roomDto.getSize());
        roomDao.save(room);
    }

    public void deleteRoomById(long id) {
        roomDao.deleteById(id);
    }
}
