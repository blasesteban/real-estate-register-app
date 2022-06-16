package com.example.realestateregister.dao;

import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoomJpaDao extends JpaRepository<Room, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Room r SET r.building =:building WHERE r.id =:roomId")
    void addToBuilding(@Param("roomId") long roomId, @Param("building") Building building);

    @Query("SELECT r FROM Building b JOIN b.rooms r WHERE b.id =:buildingId")
    List<Room> listRoomsByBuildingId(@Param("buildingId") long buildingId);
}
