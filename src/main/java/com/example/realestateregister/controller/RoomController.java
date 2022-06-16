package com.example.realestateregister.controller;

import com.example.realestateregister.dto.RoomDto;
import com.example.realestateregister.model.Room;
import com.example.realestateregister.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> listRooms() {
        return roomService.listRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable("id") long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public long addRoomAndReturnId(@RequestBody @Valid RoomDto room) {
        return roomService.addRoomAndReturnId(room);
    }

    @PutMapping("/{id}")
    public void updateRoomById(@RequestBody @Valid RoomDto room, @PathVariable("id") long id) {
        roomService.updateRoomById(room, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable("id") long id) {
        roomService.deleteRoomById(id);
    }
}
