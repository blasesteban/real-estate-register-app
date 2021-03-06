package com.example.realestateregister.service;

import com.example.realestateregister.dao.RoomJpaDao;
import com.example.realestateregister.dto.RoomDto;
import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Room;
import com.example.realestateregister.model.RoomType;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {
    @InjectMocks
    private RoomService roomService;
    @Mock
    private RoomJpaDao roomJpaDao;

    private final Room room1 = new Room(1, RoomType.DINING_ROOM, 1, new Building());
    private final Room room2 = new Room(2, RoomType.LIVING_ROOM, 2, new Building());
    private final Room room3 = new Room(3, RoomType.KITCHEN, 3, new Building());
    private final List<Room> roomList = List.of(room1, room2, room3);

    private RoomDto roleDtoFromEntity(Room room) {
        return new RoomDto(room.getRoomType().toString(), room.getSize());
    }

    @Test
    void listRooms() {
        when(roomJpaDao.findAll()).thenReturn(roomList);
        List<Room> expected = roomService.listRooms();
        assertEquals(expected, roomList);
        assertEquals(expected.get(0), room1);
        assertEquals(expected.get(1), room2);
        assertEquals(expected.get(2), room3);
    }

    @Test
    void getRoomById() {
        when(roomJpaDao.findById(1L)).thenReturn(Optional.of(room1));
        Room expected = roomService.getRoomById(1);
        assertEquals(expected, room1);
        assertEquals(expected.getBuilding(), room1.getBuilding());
    }

/*    @Test
    void addRoomAndReturnId() {
    }*/

    @SneakyThrows
    @Test
    void updateRoomById() {
        when(roomJpaDao.findById(room1.getId())).thenReturn(Optional.of(room1));
        roomService.updateRoomById(roleDtoFromEntity(room1), room1.getId());
        verify(roomJpaDao).save(room1);
    }

    @Test
    void deleteRoomById() {
        roomService.deleteRoomById(room1.getId());
        verify(roomJpaDao).deleteById(room1.getId());
    }
}