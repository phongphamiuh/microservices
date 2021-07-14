package com.hotel.project.repository;

import com.hotel.project.domain.HotelFacilities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelFacilitiesRepository extends JpaRepository<HotelFacilities,Long> {
    Optional<HotelFacilities> findByName(String name);
}
