package com.example.realestateregister;

import com.example.realestateregister.dto.*;
import com.example.realestateregister.model.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RealEstateRegisterApplicationTests {
    private final String BUILDING_URL = "/building";
    private final String PERSON_URL = "/person";
    private final String ROLE_URL = "/role";
    private final String ROOM_URL = "/room";
    private static long BUILDING_ID = 1;
    private static long PERSON_ID = 1;
    private static long ROLE_ID = 1;
    private static long ROOM_ID = 1;
    @Autowired
    private TestRestTemplate restTemplate;

    private static final BuildingDto buildingDto1 = new BuildingDto(100, 150);
    private static final BuildingDto buildingDto2 = new BuildingDto(150, 200);
    private static final BuildingDto buildingDto3 = new BuildingDto(200, 250);
    private static final List<BuildingDto> buildingDtoList = List.of(buildingDto1, buildingDto2, buildingDto3);
    private static final List<Building> expectedBuildingList = new ArrayList<>();
    private static final PersonDto personDto1 = new PersonDto("Ana", "Taylor", "1011 bp", "0036101111111");
    private static final PersonDto personDto2 = new PersonDto("Bea", "Smith", "1021 bp", "0036202222222");
    private static final PersonDto personDto3 = new PersonDto("Cecil", "Winston", "1031 bp", "0036303333333");
    private static final List<PersonDto> personDtoList = List.of(personDto1, personDto2, personDto3);
    private static final List<Person> expectedPersonList = new ArrayList<>();
    private static final RoleDto roleDto1 = new RoleDto(RoleType.ARCHITECT.toString());
    private static final RoleDto roleDto2 = new RoleDto(RoleType.OWNER.toString());
    private static final RoleDto roleDto3 = new RoleDto(RoleType.REALTOR.toString());
    private static final List<RoleDto> roleDtoList = List.of(roleDto1, roleDto2, roleDto3);
    private static final List<Role> expectedRoleList = new ArrayList<>();
    private static final RoomDto roomDto1 = new RoomDto(RoomType.BEDROOM.toString(), 1);
    private static final RoomDto roomDto2 = new RoomDto(RoomType.LIVING_ROOM.toString(), 1);
    private static final RoomDto roomDto3 = new RoomDto(RoomType.KITCHEN.toString(), 1);
    private static final List<RoomDto> roomDtoList = List.of(roomDto1, roomDto2, roomDto3);
    private static final List<Room> expectedRoomList = new ArrayList<>();

    @Test
    @Order(1)
    void addBuilding() {
        long expectedId = BUILDING_ID;
        long id = Long.parseLong(postBuilding(buildingDto1).getBody());
        assertEquals(expectedId, id);
    }

    @Test
    @Order(2)
    void getAllBuildings() {
        for (BuildingDto buildingDto : buildingDtoList) {
            postBuilding(buildingDto);
        }
        ResponseEntity<Building[]> response = restTemplate.getForEntity(BUILDING_URL, Building[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Building[] actualArr = response.getBody();
        Building[] expected = expectedBuildingList.toArray(new Building[0]);
        assertArraysHasSameElements(expected, actualArr);
    }

    @Test
    @Order(3)
    void updateBuilding() {
        long id = 1;
        final HttpEntity<?> httpEntity = createObjectHttpEntityWithMediatypeJson(buildingDto3);
        restTemplate.put(BUILDING_URL + "/" + id, httpEntity);
        findExpectedBuildingById(id).setSquareMeters(buildingDto3.getSquareMeters());
        findExpectedBuildingById(id).setPrice(buildingDto3.getPrice());
        ResponseEntity<Building[]> response = restTemplate.getForEntity(BUILDING_URL, Building[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Building[] actualArr = response.getBody();
        Building[] expected = expectedBuildingList.toArray(new Building[0]);
        assertArraysHasSameElements(expected, actualArr);
        assertSameBuilding(findExpectedBuildingById(id), Arrays.stream(actualArr).filter(building -> building.getId() == id).findFirst().orElseThrow());
    }

    @Test
    @Order(4)
    void deleteBuilding() {
        long id = 1;
        restTemplate.delete(BUILDING_URL + "/" + id);
        expectedBuildingList.remove(findExpectedBuildingById(id));
        ResponseEntity<Building[]> response = restTemplate.getForEntity(BUILDING_URL, Building[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Building[] actualArr = response.getBody();
        Building[] expected = expectedBuildingList.toArray(new Building[0]);
        assertArraysHasSameElements(expected, actualArr);
    }

    @Test
    @Order(5)
    void addPerson() {
        long expectedId = PERSON_ID;
        long id = Long.parseLong(postPerson(personDto1).getBody());
        assertEquals(expectedId, id);
    }

    @Test
    @Order(6)
    void getAllPersons() {
        for (PersonDto personDto : personDtoList) {
            postPerson(personDto);
        }
        ResponseEntity<Person[]> response = restTemplate.getForEntity(PERSON_URL, Person[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Person[] actualArr = response.getBody();
        Person[] expected = expectedPersonList.toArray(new Person[0]);
        assertArraysHasSameElements(expected, actualArr);
    }

    @Test
    @Order(7)
    void updatePerson() {
        long id = 1;
        final HttpEntity<?> httpEntity = createObjectHttpEntityWithMediatypeJson(personDto3);
        restTemplate.put(PERSON_URL + "/" + id, httpEntity);
        findExpectedPersonById(id).setFirstname(personDto3.getFirstname());
        findExpectedPersonById(id).setSurname(personDto3.getSurname());
        findExpectedPersonById(id).setAddress(personDto3.getAddress());
        findExpectedPersonById(id).setPhoneNumber(personDto3.getPhoneNumber());
        ResponseEntity<Person[]> response = restTemplate.getForEntity(PERSON_URL, Person[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Person[] actualArr = response.getBody();
        Person[] expected = expectedPersonList.toArray(new Person[0]);
        assertArraysHasSameElements(expected, actualArr);
        assertSamePerson(findExpectedPersonById(id), Arrays.stream(actualArr).filter(person -> person.getId() == id).findFirst().orElseThrow());

    }

    @Test
    @Order(8)
    void deletePerson() {
        long id = 1;
        restTemplate.delete(PERSON_URL + "/" + id);
        expectedPersonList.remove(findExpectedPersonById(id));
        ResponseEntity<Person[]> response = restTemplate.getForEntity(PERSON_URL, Person[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Person[] actualArr = response.getBody();
        Person[] expected = expectedPersonList.toArray(new Person[0]);
        assertArraysHasSameElements(expected, actualArr);
    }

    @Test
    @Order(9)
    void addRole() {
        long expectedId = ROLE_ID;
        long id = Long.parseLong(postRole(roleDto1).getBody());
        assertEquals(expectedId, id);
    }

    @Test
    @Order(10)
    void getAllRoles() {
        for (RoleDto roleDto : roleDtoList) {
            postRole(roleDto);
        }
        ResponseEntity<Role[]> response = restTemplate.getForEntity(ROLE_URL, Role[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Role[] actualArr = response.getBody();
        Role[] expected = expectedRoleList.toArray(new Role[0]);
        assertArraysHasSameElements(expected, actualArr);
    }

    @Test
    @Order(11)
    void updateRole() {
        long id = 1;
        final HttpEntity<?> httpEntity = createObjectHttpEntityWithMediatypeJson(roleDto3);
        restTemplate.put(ROLE_URL + "/" + id, httpEntity);
        findExpectedRoleById(id).setRoleType(RoleType.valueOf(roleDto3.getRoleType()));
        ResponseEntity<Role[]> response = restTemplate.getForEntity(ROLE_URL, Role[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Role[] actualArr = response.getBody();
        Role[] expected = expectedRoleList.toArray(new Role[0]);
        assertArraysHasSameElements(expected, actualArr);
        assertSameRole(findExpectedRoleById(id), Arrays.stream(actualArr).filter(role -> role.getId() == id).findFirst().orElseThrow());

    }

    @Test
    @Order(12)
    void deleteRole() {
        long id = 1;
        restTemplate.delete(ROLE_URL + "/" + id);
        expectedRoleList.remove(findExpectedRoleById(id));
        ResponseEntity<Role[]> response = restTemplate.getForEntity(ROLE_URL, Role[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Role[] actualArr = response.getBody();
        Role[] expected = expectedRoleList.toArray(new Role[0]);
        assertArraysHasSameElements(expected, actualArr);
    }

    @Test
    @Order(13)
    void addRoom() {
        long expectedId = ROOM_ID;
        long id = Long.parseLong(postRoom(roomDto1).getBody());
        assertEquals(expectedId, id);
    }

    @Test
    @Order(14)
    void getAllRooms() {
        for (RoomDto roomDto : roomDtoList) {
            postRoom(roomDto);
        }
        ResponseEntity<Room[]> response = restTemplate.getForEntity(ROOM_URL, Room[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Room[] actualArr = response.getBody();
        Room[] expected = expectedRoomList.toArray(new Room[0]);
        assertArraysHasSameElements(expected, actualArr);
    }

    @Test
    @Order(15)
    void updateRoom() {
        long id = 1;
        final HttpEntity<?> httpEntity = createObjectHttpEntityWithMediatypeJson(roomDto3);
        restTemplate.put(ROOM_URL + "/" + id, httpEntity);
        findExpectedRoomById(id).setRoomType(RoomType.valueOf(roomDto3.getRoomType()));
        findExpectedRoomById(id).setSize(roomDto3.getSize());
        ResponseEntity<Room[]> response = restTemplate.getForEntity(ROOM_URL, Room[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Room[] actualArr = response.getBody();
        Room[] expected = expectedRoomList.toArray(new Room[0]);
        assertArraysHasSameElements(expected, actualArr);
        assertSameRoom(findExpectedRoomById(id), Arrays.stream(actualArr).filter(room -> room.getId() == id).findFirst().orElseThrow());
    }

    @Test
    @Order(16)
    void deleteRoom() {
        long id = 1;
        restTemplate.delete(ROOM_URL + "/" + id);
        expectedRoomList.remove(findExpectedRoomById(id));
        ResponseEntity<Room[]> response = restTemplate.getForEntity(ROOM_URL, Room[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Room[] actualArr = response.getBody();
        Room[] expected = expectedRoomList.toArray(new Room[0]);
        assertArraysHasSameElements(expected, actualArr);
    }

    @Test
    @Order(17)
    void getBuilding() {
        long id = BUILDING_ID;
        postBuilding(buildingDto1);
        addRoomsToBuilding(id, expectedRoomList);
        expectedRoomList.forEach(room -> room.setBuilding(findExpectedBuildingById(id)));
        findExpectedBuildingById(id).setRooms(expectedRoomList);
        addRolesToBuilding(id, expectedRoleList);
        expectedRoleList.forEach(role -> role.setBuilding(findExpectedBuildingById(id)));
        findExpectedBuildingById(id).setRoles(expectedRoleList);
        Building expectedBuilding = findExpectedBuildingById(id);
        final ResponseEntity<Building> response = restTemplate.getForEntity(BUILDING_URL + "/" + id, Building.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSameBuilding(expectedBuilding, response.getBody());
    }

    @Test
    @Order(18)
    void getPerson() {
        long id = PERSON_ID;
        postPerson(personDto1);
        addRolesToPerson(id, expectedRoleList);
        expectedRoleList.forEach(role -> role.setPerson(findExpectedPersonById(id)));
        findExpectedPersonById(id).setRoles(expectedRoleList);
        Person expectedPerson = findExpectedPersonById(id);
        final ResponseEntity<Person> response = restTemplate.getForEntity(PERSON_URL + "/" + id, Person.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSamePerson(expectedPerson, response.getBody());
    }

    @Test
    @Order(19)
    void getRole() {
        long id = ROLE_ID - 1;
        Role expectedRole = findExpectedRoleById(id);
        final ResponseEntity<Role> response = restTemplate.getForEntity(ROLE_URL + "/" + id, Role.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSameRole(expectedRole, response.getBody());
    }

    @Test
    @Order(20)
    void getRoom() {
        long id = ROOM_ID - 1;
        Room expectedRole = findExpectedRoomById(id);
        final ResponseEntity<Room> response = restTemplate.getForEntity(ROOM_URL + "/" + id, Room.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSameRoom(expectedRole, response.getBody());
    }

    private void addRoomsToBuilding(long buildingId, List<Room> roomList) {
        for (Room room : roomList) {
            restTemplate.postForEntity(BUILDING_URL + "/room", new BuildingRoomDto(buildingId, room.getId()), String.class);
        }
    }

    private void addRolesToBuilding(long buildingId, List<Role> roleList) {
        for (Role role : roleList) {
            restTemplate.postForEntity(BUILDING_URL + "/role", new BuildingRoleDto(buildingId, role.getId()), String.class);
        }
    }

    private void addRolesToPerson(long personId, List<Role> roleList) {
        for (Role role : roleList) {
            restTemplate.postForEntity(PERSON_URL + "/role", new PersonRoleDto(personId, role.getId()), String.class);
        }
    }

    private Building findExpectedBuildingById(long id) {
        return expectedBuildingList.stream().filter(building -> building.getId() == id).findFirst().orElseThrow();
    }

    private Person findExpectedPersonById(long id) {
        return expectedPersonList.stream().filter(person -> person.getId() == id).findFirst().orElseThrow();
    }

    private Role findExpectedRoleById(long id) {
        return expectedRoleList.stream().filter(role -> role.getId() == id).findFirst().orElseThrow();
    }

    private Room findExpectedRoomById(long id) {
        return expectedRoomList.stream().filter(room -> room.getId() == id).findFirst().orElseThrow();
    }

    private void assertSameBuilding(Building expected, Building actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getSquareMeters(), actual.getSquareMeters());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertListsHasSameElements(Collections.singletonList(expected.getRooms()), Collections.singletonList(actual.getRooms()));
        assertListsHasSameElements(Collections.singletonList(expected.getRoles()), Collections.singletonList(actual.getRoles()));
    }

    private void assertSamePerson(Person expected, Person actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getFirstname(), actual.getFirstname());
        assertEquals(expected.getSurname(), actual.getSurname());
        assertEquals(expected.getAddress(), actual.getAddress());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        assertListsHasSameElements(Collections.singletonList(expected.getRoles()), Collections.singletonList(actual.getRoles()));
    }

    private void assertSameRole(Role expected, Role actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getRoleType(), actual.getRoleType());
    }

    private void assertSameRoom(Room expected, Room actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getRoomType(), actual.getRoomType());
        assertEquals(expected.getSize(), actual.getSize());
    }

    private ResponseEntity<String> postBuilding(BuildingDto buildingDto) {
        expectedBuildingList.add(new Building(BUILDING_ID, buildingDto.getSquareMeters(), buildingDto.getPrice(), new ArrayList<>(), new ArrayList<>()));
        BUILDING_ID++;
        final HttpEntity<?> httpEntity = createObjectHttpEntityWithMediatypeJson(buildingDto);
        return restTemplate.postForEntity(BUILDING_URL, httpEntity, String.class);
    }

    private ResponseEntity<String> postPerson(PersonDto personDto) {
        expectedPersonList.add(new Person(PERSON_ID, personDto.getFirstname(), personDto.getSurname(), personDto.getAddress(), personDto.getPhoneNumber(), new ArrayList<>()));
        PERSON_ID++;
        final HttpEntity<?> httpEntity = createObjectHttpEntityWithMediatypeJson(personDto);
        return restTemplate.postForEntity(PERSON_URL, httpEntity, String.class);
    }

    private ResponseEntity<String> postRole(RoleDto roleDto) {
        expectedRoleList.add(new Role(ROLE_ID, RoleType.valueOf(roleDto.getRoleType()), null, null));
        ROLE_ID++;
        final HttpEntity<?> httpEntity = createObjectHttpEntityWithMediatypeJson(roleDto);
        return restTemplate.postForEntity(ROLE_URL, httpEntity, String.class);
    }

    private ResponseEntity<String> postRoom(RoomDto roomDto) {
        expectedRoomList.add(new Room(ROOM_ID, RoomType.valueOf(roomDto.getRoomType()), roomDto.getSize(), null));
        ROOM_ID++;
        final HttpEntity<?> httpEntity = createObjectHttpEntityWithMediatypeJson(roomDto);
        return restTemplate.postForEntity(ROOM_URL, httpEntity, String.class);
    }

    private HttpEntity<?> createObjectHttpEntityWithMediatypeJson(Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(object, headers);
    }

    private void assertArraysHasSameElements(Object[] expectedArr, Object[] actualArr) {
        List<Object> expected = Arrays.asList(expectedArr);
        List<Object> actual = Arrays.asList(actualArr);
        assertListsHasSameElements(expected, actual);
    }

    private void assertListsHasSameElements(List<Object> expected, List<Object> actual) {
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual) && actual.containsAll(expected));
    }
}
