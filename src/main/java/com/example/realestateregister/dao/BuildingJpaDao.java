package com.example.realestateregister.dao;

import com.example.realestateregister.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingJpaDao extends JpaRepository<Building, Long> {
}
