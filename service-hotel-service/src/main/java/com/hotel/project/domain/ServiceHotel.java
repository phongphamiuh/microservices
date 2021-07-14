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
public class ServiceHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="service_hotel_id")
    private Long serviceHotelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_facilities_id",referencedColumnName = "hotel_facilities_id")
    private HotelFacilities hotelFacilities;
    private Long hotelId;
    private double price;
    private int tax;
    private String taxName;
    private boolean priceAble;
    private boolean checkAble;
    private Instant modifiedDate;
}
