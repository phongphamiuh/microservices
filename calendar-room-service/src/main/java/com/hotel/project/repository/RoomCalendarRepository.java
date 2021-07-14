package com.hotel.project.repository;

import com.hotel.project.dto.RoomCalendarResponse;
import com.hotel.project.model.AvailableDate;
import com.hotel.project.model.RoomCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomCalendarRepository extends JpaRepository<RoomCalendar,Long> {
    List<RoomCalendar> findAllByAvailableDate(AvailableDate availableDate);
    Optional<RoomCalendar> findByAvailableDateAndRoomId(AvailableDate availableDate,Long roomId);

    @Query("from RoomCalendar r join r.availableDate a where a.date=:date and r.roomId=:roomId")
    RoomCalendar findByDateAndRoomId(@Param("date") LocalDate date,@Param("roomId") Long roomId);

    @Query("from RoomCalendar r join r.availableDate a where a.date=:date and r.roomId=:roomId and a.hotelId=:hotelId")
    RoomCalendar findByDateAndRoomIdAndHotelId(@Param("date") LocalDate date,@Param("roomId") Long roomId,@Param("hotelId")Long hotelId);

    @Query("from RoomCalendar r join r.availableDate a where a.hotelId=:hotelId")
    List<RoomCalendar> findALlByAvailableDateHotelId(@Param("hotelId") Long hotelId);

    @Query("from RoomCalendar r join r.availableDate a where a.date=:date and a.hotelId=:hotelId")
    List<RoomCalendar> findAllByDateNowAndHotelId(@Param("date") LocalDate date,@Param("hotelId") Long hotelId);

    @Query("from RoomCalendar r join r.availableDate a " +
            "where a.hotelId=:hotelId and a.date between :startDate and :endDate " +
            "group by r.roomId " +
            "having count(*)=DATEDIFF(:endDate,:startDate)+1")
    //@Query(value="from RoomCalendar r join r.availableDate a where a.hotelId=:hotelId and a.date between :startDate and :endDate")
    List<RoomCalendar> findAllByHotelIdAndBeweentDates(@Param("hotelId") Long hotelId,
                                                       @Param("startDate") LocalDate startDate,

                                                       @Param("endDate") LocalDate endDate);
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query(value = "Update Roomcalendar r" +
//            "set r.quantity=:quantity where r.roomCalendarId in (select r1.roomCalendarId from Roomcalendar r1 inner join r1.availableDate a " +
//            "where a.date=:date and a.hotelId=:hotelId)" )
//    void updatQuantityByDateAndHotelId(@Param("quantity") Integer quantity,
//                                       @Param("date") LocalDate date,
//                                       @Param("hotelId") Long hotelId);

}
