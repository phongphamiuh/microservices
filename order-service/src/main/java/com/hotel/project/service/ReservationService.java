package com.hotel.project.service;

import com.hotel.project.domain.Reservation;
import com.hotel.project.domain.ReservationStatus;
import com.hotel.project.domain.request.ReservationRequest;
import com.hotel.project.domain.response.ReservationResponse;
import com.hotel.project.domain.response.ReservationResponseDetail;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    ReservationResponseDetail save(ReservationRequest reservationRequest);
    ReservationResponseDetail getReservationByReservationId(Long reservationId);
    List<ReservationResponse> findAllReservationByToday(LocalDate star, LocalDate end, Long hotelId,
                                                        ReservationStatus reservationStatus,String text);

    ReservationResponseDetail updateReservation(ReservationRequest reservationRequest);
}
