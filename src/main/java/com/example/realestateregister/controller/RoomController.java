package com.example.realestateregister.controller;

import com.example.realestateregister.dto.RoomDto;
import com.example.realestateregister.model.Room;
import com.example.realestateregister.service.RoomService;
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

    @GetMapping
    public List<Room> listRooms() {
        return roomService.listRooms();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable("id") long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    public ResponseEntity<?> addRoomAndReturnId(@RequestBody @Valid RoomDto room, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid room");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid room");
        }
        return ResponseEntity.ok(roomService.addRoomAndReturnId(room));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoomById(@RequestBody @Valid RoomDto room, @PathVariable("id") long id, BindingResult br) {
        if (br.hasErrors()) {
            logger.error("invalid room");
            br.getAllErrors().forEach(e -> logger.error(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body("invalid room");
        }
        roomService.updateRoomById(room, id);
        return ResponseEntity.ok().body("room is updated");
    }

    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable("id") long id) {
        roomService.deleteRoomById(id);
    }
}
