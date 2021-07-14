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
public class ReservationResponseDetail {
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
    private String nameReservation;
    private String phone;
    private String city;
    private String comment;
    private String email;
    private ReservationStatus reservationStatus;
    private List<ReservationDetailResponse> reservationDetails;
}
