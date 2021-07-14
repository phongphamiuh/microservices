package com.hotel.project.repository;

import com.hotel.project.entity.Facilities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilitiesRepository extends JpaRepository<Facilities,Long> {
    Optional<Facilities> findByName(String name);
}
