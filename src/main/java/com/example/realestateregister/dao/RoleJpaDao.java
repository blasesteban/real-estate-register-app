package com.example.realestateregister.dao;

import com.example.realestateregister.model.Building;
import com.example.realestateregister.model.Person;
import com.example.realestateregister.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoleJpaDao extends JpaRepository<Role, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Role r SET r.building =:building WHERE r.id =:roleId")
    void addToBuilding(@Param("roleId") Long roleId, @Param("building") Building building);

    @Modifying
    @Transactional
    @Query("UPDATE Role r SET r.person =:person WHERE r.id =:roleId")
    void addToPerson(@Param("roleId") Long roleId, @Param("person") Person person);

    @Query("SELECT r FROM Building b JOIN b.roles r WHERE b.id =:buildingId")
    List<Role> listRolesByBuildingId(@Param("buildingId") long id);

    @Query("SELECT r FROM Person p JOIN p.roles r WHERE p.id =:personId")
    List<Role> listRolesByPerson(@Param("personId") long id);
}