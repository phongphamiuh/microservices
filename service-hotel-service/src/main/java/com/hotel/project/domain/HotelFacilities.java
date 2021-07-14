package com.hotel.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelFacilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hotel_facilities_id")
    private Long hotelFacilitiesId;
    private String name;
    private String icon;
}
