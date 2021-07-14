package com.hotel.project.domain.response;

import com.hotel.project.domain.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private Long reservationId;
    private String nameReservation;
    private LocalDate created;
    private LocalDate checkin;
    private LocalDate checkout;
    private ReservationStatus reservationStatus;
    private double balance;
    private double subTotal;

}
