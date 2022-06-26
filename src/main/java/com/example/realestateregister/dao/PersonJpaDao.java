package com.example.realestateregister.dao;

import com.example.realestateregister.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonJpaDao extends JpaRepository<Person, Long> {
    @Query("select r.roleType, r.person from Role r where r.building.id=:id")
    List<Object[]> listPersonsByBuilding(@Param("id") long id);
}
