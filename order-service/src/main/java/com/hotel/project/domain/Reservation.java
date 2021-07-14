package com.hotel.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long reservationId;
    private LocalDate created;
    private LocalDate checkin;
    private LocalDate checkout;
    private int night;
    private int guest;
    private int tax;
    private double subTotal;
    private double paid;
    private double balance;
    private Long hotelId;
    private Long userId;
    private Instant modifiedDate;
    private String nameReservation;
    private String phone;
    private String city;
    private String comment;
    private String email;
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @OneToMany(targetEntity = ReservationDetail.class,cascade = CascadeType.ALL)
    @JoinColumn(name="order_id",referencedColumnName = "order_id")
    private List<ReservationDetail> reservationDetails;
}
