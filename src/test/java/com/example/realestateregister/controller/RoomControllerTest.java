package com.example.realestateregister.controller;

import com.example.realestateregister.dto.RoomDto;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Room;
import com.example.realestateregister.model.RoomType;
import com.example.realestateregister.service.RoomService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
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

    private RoomDto roomDtoFromEntity(Room room) {
        return new RoomDto(room.getRoomType().toString(), room.getSize());
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

    @SneakyThrows
    @Test
    void addRoomAndReturnId() {
        when(br.hasErrors()).thenReturn(false);
        when(roomService.addRoomAndReturnId(roomDtoFromEntity(room1))).thenReturn(room1.getId());
        ResponseEntity<?> expected = roomController.addRoomAndReturnId(roomDtoFromEntity(room1), br);
        assertEquals(expected.getBody(), room1.getId());
    }

    @Test
    void addRoomAndReturnIdWrong() {
        when(br.hasErrors()).thenReturn(true);
        ResponseEntity<?> expected = roomController.addRoomAndReturnId(new RoomDto(), br);
        assertEquals(expected.getBody(), "invalid room");
    }

    @SneakyThrows
    @Test
    void updateRoomById() {
        when(br.hasErrors()).thenReturn(false);
        ResponseEntity<?> expected = roomController.updateRoomById(roomDtoFromEntity(room1), room1.getId(), br);
        verify(roomService).updateRoomById(roomDtoFromEntity(room1), room1.getId());
        assertEquals(expected.getBody(), "room is updated");
    }

    @Test
    void updateRoomByIdWrong() {
        when(br.hasErrors()).thenReturn(true);
        ResponseEntity<?> expected = roomController.updateRoomById(new RoomDto(), room1.getId(), br);
        assertEquals(expected.getBody(), "invalid room");
    }

    @Test
    void deleteRoomById() {
        roomController.deleteRoomById(room1.getId());
        verify(roomService).deleteRoomById(room1.getId());
    }
}