package com.hotel.project.service;

import com.hotel.project.domain.Reservation;
import com.hotel.project.domain.ReservationDetail;
import com.hotel.project.domain.ReservationStatus;
import com.hotel.project.domain.request.ReservationRequest;
import com.hotel.project.domain.response.ReservationResponse;
import com.hotel.project.domain.response.ReservationResponseDetail;
import com.hotel.project.domain.specs.ReservationSpecs;
import com.hotel.project.exception.BadRequestException;
import com.hotel.project.mapper.ReservationMapper;
import com.hotel.project.repository.ReservationRepository;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public ReservationResponseDetail save(ReservationRequest reservationRequest) {
        Reservation reservation=reservationMapper.map(reservationRequest);
        List<ReservationDetail> reservationDetails=new ArrayList<>();
        reservationRequest.getReservationDetails().forEach(rd->{
            ReservationDetail reservationDetail=ReservationDetail.builder()
                    .quantity(rd.getQuantity())
                    .lineTotal(rd.getUnitPrice() * rd.getQuantity())
                    .roomId(rd.getRoomId())
                    .unitPrice(rd.getUnitPrice())
                    .unitPriceDiscount(rd.getUnitPriceDiscount())
                    .build();
            reservationDetails.add(reservationDetail);
        });
        reservation.setReservationDetails(reservationDetails);
        reservationRepository.save(reservation);
        ReservationResponseDetail reservationResponse=reservationMapper.mapToReservationDto(reservation);
        return reservationResponse;
    }

    @Override
    public ReservationResponseDetail getReservationByReservationId(Long reservationId) {
        log.info(SecurityContextHolder.getContext().getAuthentication().getName());
        Reservation reservation=reservationRepository.findById(
                reservationId).orElseThrow(()->new BadRequestException("Reservation not found"));
        ReservationResponseDetail reservationResponse=reservationMapper.mapToReservationDto(reservation);
        return reservationResponse;
    }

    //find reservation
    @Override
    public List<ReservationResponse> findAllReservationByToday(LocalDate startDate, LocalDate endDate, Long hotelId, ReservationStatus reservationStatus,String text) {
        return reservationRepository.findAllsWithCheckin(startDate,endDate,hotelId,reservationStatus,text)
                .stream()
                .map(reservationMapper::mapToReservationDto1)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationResponseDetail updateReservation(ReservationRequest reservationRequest) {
        Reservation reservation=reservationRepository.findById(reservationRequest.getReservationId()).orElseThrow(()->
                new BadRequestException("Reservation not found"));

        reservationMapper.updateReservation(reservationRequest,reservation);
        reservation=reservationRepository.save(reservation);
        ReservationResponseDetail reservationResponseDetail=reservationMapper.mapToReservationDto(reservation);
        return reservationResponseDetail;
    }
}
