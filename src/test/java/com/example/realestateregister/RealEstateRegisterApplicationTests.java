package com.example.realestateregister;

import com.example.realestateregister.dto.BuildingRoomDto;
import com.example.realestateregister.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RealEstateRegisterApplicationTests {
    private final String BUILDING_URL = "/building";
    private final String PERSON_URL = "/person";
    private final String ROLE_URL = "/role";
    private final String ROOM_URL = "/room";
    @Autowired
    private TestRestTemplate restTemplate;

    private static Building[] buildingArray() {
        return new Building[]{
                new Building(1, 1, 1, null, null),
                new Building(2, 2, 2, null, null),
        };
    }

    private static Person[] personArray() {
        return new Person[]{
                new Person(1, "a", "a", "a", "0036101111111", null),
                new Person(2, "b", "b", "b", "0036202222222", null),
                new Person(3, "c", "c", "c", "0036303333333", null),
        };
    }

    private static Role[] roleArray() {
        return new Role[]{
                new Role(1, RoleType.ARCHITECT, null, null),
                new Role(2, RoleType.OWNER, null, null),
                new Role(3, RoleType.REALTOR, null, null),
        };
    }

    private static Room[] roomArray() {
        return new Room[]{
                new Room(1, RoomType.DINING_ROOM, 1, null),
                new Room(2, RoomType.GUEST_ROOM, 2, null),
                new Room(3, RoomType.LIVING_ROOM, 3, null),
        };
    }

    @Test
    void getAllBuildings() {
        Building[] expectedArr = buildingArray();
        for (Building building : buildingArray()) {
            postBuilding(BUILDING_URL, building);
        }
        final ResponseEntity<Building[]> response = restTemplate.getForEntity(BUILDING_URL, Building[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Building[] actualArr = response.getBody();
        assertBuildingArraysHasSameElements(expectedArr, actualArr);
    }

    @Test
    void getBuilding() {
        final long BUILDING_ID = 1L;
        Room[] roomArray = roomArray();
        for (Room room : roomArray) {
            postRoom(ROOM_URL, room);
        }
        List<Room> roomList = new ArrayList<>() {{
            addAll(Arrays.stream(roomArray()).toList());
        }};
        Building building = new Building(BUILDING_ID, 1, 1, Arrays.stream(roomArray()).toList(), null);
        postBuilding(BUILDING_URL, building);
        addRoomsToBuilding(BUILDING_ID, roomList);
        Building expectedBuilding = new Building(BUILDING_ID, 1, 1, Arrays.stream(roomArray()).toList(), null);
        final ResponseEntity<Building> response = restTemplate.getForEntity(BUILDING_URL + "/" + BUILDING_ID, Building.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSameBuilding(expectedBuilding, Objects.requireNonNull(response.getBody()));
    }

    private void addRoomsToBuilding(long buildingId, List<Room> roomList) {
        for (Room room : roomList) {
            restTemplate.postForEntity(BUILDING_URL + "/room", new BuildingRoomDto(buildingId, room.getId()), String.class);
        }
    }

    private void assertSameBuilding(Building expected, Building actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getSquareMeters(), actual.getSquareMeters());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getRooms(), actual.getRooms());
    }

    @Test
    void getAllRooms() {
        Room[] expectedArr = roomArray();
        for (Room room : roomArray()) {
            postRoom(BUILDING_URL, room);
        }
        final ResponseEntity<Room[]> response = restTemplate.getForEntity(ROOM_URL, Room[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Room[] actualArr = response.getBody();
        assertRoomArraysHasSameElements(expectedArr, actualArr);
    }

    private HttpEntity<Building> createBuildingHttpEntityWithMediatypeJson(Building building) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(building, headers);
    }

    private HttpEntity<Room> createRoomHttpEntityWithMediatypeJson(Room room) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(room, headers);
    }

    private void postBuilding(String url, Building building) {
        final HttpEntity<Building> httpEntity = createBuildingHttpEntityWithMediatypeJson(building);
        restTemplate.postForEntity(url, httpEntity, String.class);
    }

    private void postRoom(String url, Room room) {
        final HttpEntity<Room> httpEntity = createRoomHttpEntityWithMediatypeJson(room);
        restTemplate.postForEntity(url, httpEntity, String.class);
    }

    private void assertBuildingArraysHasSameElements(Building[] expectedArr, Building[] actualArr) {
        List<Building> expected = Arrays.asList(expectedArr);
        List<Building> actual = Arrays.asList(actualArr);
        assertBuildingListsHasSameElements(expected, actual);
    }

    private void assertRoomArraysHasSameElements(Room[] expectedArr, Room[] actualArr) {
        List<Room> expected = Arrays.asList(expectedArr);
        List<Room> actual = Arrays.asList(actualArr);
        assertRoomListsHasSameElements(expected, actual);
    }

    private void assertBuildingListsHasSameElements(List<Building> expected, List<Building> actual) {
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }

    private void assertRoomListsHasSameElements(List<Room> expected, List<Room> actual) {
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }
}
