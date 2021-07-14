package com.hotel.project.repository;

import com.hotel.project.domain.Reservation;
import com.hotel.project.domain.ReservationStatus;
import com.hotel.project.domain.response.ReservationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation,Long>,JpaRepository<Reservation,Long>, JpaSpecificationExecutor<Reservation> {
    @Query("from Reservation r where r.checkin between :start and :end and hotelId=:hotelId " +
            "and (:reservationStatus is null or r.reservationStatus=:reservationStatus) " +
            "and (r.nameReservation like %:text% or r.checkin like %:text% or r.checkout like %:text% " +
            "or r.reservationStatus like %:text%)")
    List<Reservation> findAllsWithCheckin(@Param("start") LocalDate start,
                                          @Param("end") LocalDate end,
                                          @Param("hotelId") Long hotelId,
                                          @Param("reservationStatus")ReservationStatus reservationStatus,
                                          @Param("text")String text);

}
