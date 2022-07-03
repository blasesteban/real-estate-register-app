package com.example.realestateregister.controller;

import com.example.realestateregister.dto.RoomDto;
import com.example.realestateregister.exceptions.InvalidRoomTypeException;
import com.example.realestateregister.model.Room;
import com.example.realestateregister.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;
    private final Logger logger = LoggerFactory.getLogger(RoomController.class);

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @Operation(summary = "List all rooms", description = "Lists all the rooms")
    @GetMapping
    public List<Room> listRooms() {
        return roomService.listRooms();
    }

    @Operation(summary = "Get room", description = "Gets a room by id")
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable("id") long id) {
        return roomService.getRoomById(id);
    }

    @Operation(summary = "Add room", description = "Adds a new room")
    @PostMapping
    public ResponseEntity<?> addRoomAndReturnId(@RequestBody @Valid RoomDto room, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid room");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid room");
        }
        try {
            return ResponseEntity.ok(roomService.addRoomAndReturnId(room));
        } catch (InvalidRoomTypeException e) {
            return ResponseEntity.badRequest().body("invalid roomType");
        }
    }

    @Operation(summary = "Update room", description = "Updates a room by id")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoomById(@RequestBody @Valid RoomDto room, @PathVariable("id") long id, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid room");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid room");
        }
        try {
            roomService.updateRoomById(room, id);
        } catch (InvalidRoomTypeException e) {
            return ResponseEntity.badRequest().body("invalid roomType");
        }
        return ResponseEntity.ok("room is updated");
    }

    @Operation(summary = "Delete room", description = "Deletes a room by id")
    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable("id") long id) {
        roomService.deleteRoomById(id);
    }
}
