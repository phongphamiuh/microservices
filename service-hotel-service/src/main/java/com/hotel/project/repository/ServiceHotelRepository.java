package com.hotel.project.repository;


import com.hotel.project.domain.ServiceHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceHotelRepository extends JpaRepository<ServiceHotel,Long> {
    @Query("from ServiceHotel where hotelId=:hotelId and checkAble=:checkAble")
    List<ServiceHotel> findAllByHotelIdAndCheckAble(@Param("hotelId") Long hotelId,
                                                          @Param("checkAble")boolean checkAble);

    List<ServiceHotel> findAllByHotelId(Long hotelId);

    @Query("from ServiceHotel where hotelFacilities.name=:name and hotelId=:hotelId")
    ServiceHotel findByNameAndHotelId(@Param("name") String name,@Param("hotelId") Long hotelId);

}
