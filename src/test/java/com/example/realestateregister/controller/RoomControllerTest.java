package com.example.realestateregister.controller;

import com.example.realestateregister.dto.RoomDto;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Room;
import com.example.realestateregister.model.RoomType;
import com.example.realestateregister.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {
    @InjectMocks
    private RoomController roomController;
    @Mock
    private BindingResult br;
    @Mock
    private RoomService roomService;

    private final Room room1 = new Room(1, RoomType.DINING_ROOM, 1, new Building());
    private final Room room2 = new Room(2, RoomType.LIVING_ROOM, 2, new Building());
    private final Room room3 = new Room(3, RoomType.KITCHEN, 3, new Building());
    private final List<Room> roomList = List.of(room1, room2, room3);

    private RoomDto roleDtoFromEntity(Room room) {
        return new RoomDto(room.getRoomType(), room.getSize());
    }

    @Test
    void listRooms() {
        when(roomService.listRooms()).thenReturn(roomList);
        List<Room> expected = roomController.listRooms();
        assertEquals(expected, roomList);
        assertEquals(expected.get(0), room1);
        assertEquals(expected.get(1), room2);
        assertEquals(expected.get(2), room3);
    }

    @Test
    void getRoomById() {
        when(roomService.getRoomById(1)).thenReturn(room1);
        Room expected = roomController.getRoomById(1);
        assertEquals(expected, room1);
    }

    @Test
    void addRoomAndReturnId() {
        when(br.hasErrors()).thenReturn(false);
        when(roomService.addRoomAndReturnId(new RoomDto(room1.getRoomType(), room1.getSize()))).thenReturn(room1.getId());
        ResponseEntity<?> expected = roomController.addRoomAndReturnId(new RoomDto(room1.getRoomType(), room1.getSize()), br);
        assertEquals(expected.getBody(), room1.getId());
    }

/*    @Test
    void updateRoomById() {
    }

    @Test
    void deleteRoomById() {
    }*/
}