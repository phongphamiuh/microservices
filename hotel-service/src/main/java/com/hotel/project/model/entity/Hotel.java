package com.hotel.project.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hotel_id")
    private Long hotelId;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="district")
    private String district;

    @Column(name="city")
    private String city;

    @Column(name="star")
    private StarType star;

    @Column(name="description")
    private String description;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="checkin")
    private Instant checkin;

    @Column(name="checkout")
    private Instant checkout;

    @Column(name="logo_url")
    private String logoUrl;

    @Column(name="display_price",columnDefinition = "double default 0")
    private Double displayPrice;

    private Instant modifiedDate;

    @Column(name="user_id")
    private Long userId;

    @PrePersist
    public void prePersis(){
        if(displayPrice==null)
            displayPrice=0.0;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @JsonDeserialize(as = Category.class)
    private Category category;
}
