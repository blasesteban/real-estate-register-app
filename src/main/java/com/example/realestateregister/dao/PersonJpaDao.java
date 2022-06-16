package com.example.realestateregister.dao;

import com.example.realestateregister.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaDao extends JpaRepository<Person, Long> {
}
