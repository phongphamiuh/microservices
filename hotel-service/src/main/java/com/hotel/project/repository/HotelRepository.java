package com.hotel.project.repository;

import com.hotel.project.model.entity.Category;
import com.hotel.project.model.entity.Hotel;
import com.hotel.project.model.entity.StarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends PagingAndSortingRepository<Hotel,Long>, JpaSpecificationExecutor<Hotel> {
    @Query("from Hotel h where (:starType is null or h.star=:starType)")
    Page<Hotel> findAllByStar(@Param("starType") StarType star, Pageable pageable);

    Page<Hotel>  findAllByCity(String city, Pageable pageable);
    Page<Hotel> findAllByCategory(Category category, Pageable pageable);
    List<Hotel> findAllByUserId(Long userId);

    @Modifying
    @Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Query("update Hotel h set h.displayPrice=:displayPrice where h.hotelId=:hotelId")
    void updateDisplayPrice(@Param("hotelId") Long hotelId,@Param("displayPrice") Double displayPrice);

    @Query("from Hotel h where h.displayPrice between :startPrice and :endPrice")
    Page<Hotel> findAllByBetweenDisplayPrice(@Param("startPrice") Double startPrice,@Param("endPrice") Double endPrice,Pageable pageable);

    Optional<Hotel> findByName(String name);
}
