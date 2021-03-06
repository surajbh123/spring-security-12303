package com.my.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.security.model.Citizen;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
       Optional <Citizen> findByEmail(String email);
       
}
