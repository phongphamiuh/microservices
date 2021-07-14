package com.hotel.project.domain.request;
import com.hotel.project.domain.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ReservationRequest {
    private Long reservationId;
    private LocalDate checkin;
    private LocalDate checkout;
    private int night;
    private int guest;
    private int tax;
    private Long hotelId;
    private Long userId;
    private String nameReservation;
    private String phone;
    private String city;
    private String comment;
    private String email;
    private ReservationStatus reservationStatus;
    private List<ReservationDetailRequest> reservationDetails;
}
