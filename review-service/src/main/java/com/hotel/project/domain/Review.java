package com.hotel.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long reviewId;
    private String title;
    private String comment;
    private LocalDate createDate;
    private float location;
    private float convention;
    private float cleanliness;
    private float price;
    private float total;
    private LocalDate checkin;
    private String roomName;
    private Long userId;
    private Long hotelId;
    private Long reservationId;
    @Enumerated(EnumType.STRING)
    private ReviewType reviewType;

    @PrePersist
    protected void onCreate() {
        createDate = LocalDate.now();
    }
    @PreUpdate
    protected void onUpdate(){
        createDate=LocalDate.now();
    }

}
