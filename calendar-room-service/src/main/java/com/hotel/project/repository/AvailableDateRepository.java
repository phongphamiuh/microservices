package com.hotel.project.repository;

import com.hotel.project.model.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AvailableDateRepository extends JpaRepository<AvailableDate,Long> {
    AvailableDate findByDateAndHotelId(LocalDate date, Long hotelId);
    List<AvailableDate> findByHotelId(Long hotelId);

    @Query("from AvailableDate a where date between :start and :end and hotelId=:hotelId")
    List<AvailableDate> findAllByHotelIdAndBetweenStarAndEndDate(@Param("hotelId") Long hotelId,
                                                                 @Param("start") LocalDate start,
                                                                 @Param("end") LocalDate end);

}
