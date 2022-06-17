package com.example.realestateregister;

import com.example.realestateregister.dto.BuildingDto;
import com.example.realestateregister.dto.BuildingRoomDto;
import com.example.realestateregister.dto.PersonDto;
import com.example.realestateregister.dto.RoomDto;
import com.example.realestateregister.model.*;
import org.junit.jupiter.api.*;
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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    private static BuildingDto[] buildingDtoArray() {
        return new BuildingDto[]{
                new BuildingDto(1, 1),
                new BuildingDto(2, 2),
        };
    }

    private static Person[] personArray() {
        return new Person[]{
                new Person(1, "a", "a", "a", "0036101111111", new ArrayList<>()),
                new Person(2, "b", "b", "b", "0036202222222", new ArrayList<>()),
                new Person(3, "c", "c", "c", "0036303333333", new ArrayList<>()),
        };
    }

    private static PersonDto[] personDtoArray() {
        return new PersonDto[]{
                new PersonDto("a", "a", "a", "0036101111111"),
                new PersonDto("b", "b", "b", "0036202222222"),
                new PersonDto("c", "c", "c", "0036303333333"),
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

    private static RoomDto[] roomDtosArray() {
        return new RoomDto[]{
                new RoomDto(RoomType.DINING_ROOM, 1),
                new RoomDto(RoomType.GUEST_ROOM, 2),
                new RoomDto(RoomType.LIVING_ROOM, 3),
        };
    }

/*    @AfterEach
    void */
    @Test
    @Order(1)
    void getAllBuildings() {
        Building[] expectedArr = buildingArray();
        for (BuildingDto buildingDto : buildingDtoArray()) {
            postObject(BUILDING_URL, buildingDto);
        }
        ResponseEntity<Building[]> response = restTemplate.getForEntity(BUILDING_URL, Building[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Building[] actualArr = response.getBody();
        assertBuildingArraysHasSameElements(expectedArr, actualArr);
    }

    @Test
    @Order(2)
    void getBuilding() {
        final long BUILDING_ID = 3;
        for (RoomDto roomDto : roomDtosArray()) {
            postRoom(ROOM_URL, roomDto);
        }
        List<Room> roomList = Arrays.asList(roomArray());
        BuildingDto buildingDto = new BuildingDto(1, 1);
        postBuilding(BUILDING_URL, buildingDto);
        addRoomsToBuilding(BUILDING_ID, roomList);
        Building expectedBuilding = new Building(BUILDING_ID, 1, 1, roomList, null);
        final ResponseEntity<Building> response = restTemplate.getForEntity(BUILDING_URL + "/" + BUILDING_ID, Building.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSameBuilding(expectedBuilding, response.getBody());
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
    @Order(3)
    void getAllRooms() {
        Room[] expectedArr = roomArray();
        for (RoomDto room : roomDtosArray()) {
            postRoom(ROOM_URL, room);
        }
        final ResponseEntity<Room[]> response = restTemplate.getForEntity(ROOM_URL, Room[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Room[] actualArr = response.getBody();
        assertRoomArraysHasSameElements(expectedArr, actualArr);
    }

    private HttpEntity<?> createObjectHttpEntityWithMediatypeJson(Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(object, headers);
    }

    private HttpEntity<BuildingDto> createBuildingHttpEntityWithMediatypeJson(BuildingDto buildingDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(buildingDto, headers);
    }

    private HttpEntity<RoomDto> createRoomHttpEntityWithMediatypeJson(RoomDto roomDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(roomDto, headers);
    }

    private void postObject(String url, Object object) {
        final HttpEntity<?> httpEntity = createObjectHttpEntityWithMediatypeJson(object);
        restTemplate.postForEntity(url, httpEntity, String.class);
    }

    private void postBuilding(String url, BuildingDto buildingDto) {
        final HttpEntity<BuildingDto> httpEntity = createBuildingHttpEntityWithMediatypeJson(buildingDto);
        restTemplate.postForEntity(url, httpEntity, String.class);
    }

    private void postRoom(String url, RoomDto roomDto) {
        final HttpEntity<RoomDto> httpEntity = createRoomHttpEntityWithMediatypeJson(roomDto);
        restTemplate.postForEntity(url, httpEntity, String.class);
    }

    private void assertArraysHasSameElements(Object[] expectedArr, Object[] actualArr) {
        List<Object> expected = Arrays.asList(expectedArr);
        List<Object> actual = Arrays.asList(actualArr);
        assertListsHasSameElements(expected, actual);
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

    private void assertListsHasSameElements(List<Object> expected, List<Object> actual) {
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }

    private void assertBuildingListsHasSameElements(List<Building> expected, List<Building> actual) {
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));

    }

    private void assertRoomListsHasSameElements(List<Room> expected, List<Room> actual) {
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }
}
