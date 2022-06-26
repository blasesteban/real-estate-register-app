package com.example.realestateregister.dao;

import com.example.realestateregister.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingJpaDao extends JpaRepository<Building, Long> {
    @Query("select r.roleType, r.building from Role r where r.person.id=:id")
    List<Object[]> listBuildingsByPerson(@Param("id") long id);
}
