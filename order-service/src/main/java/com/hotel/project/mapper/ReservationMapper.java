package com.hotel.project.mapper;

import com.hotel.project.domain.Reservation;
import com.hotel.project.domain.request.ReservationRequest;
import com.hotel.project.domain.response.ReservationResponse;
import com.hotel.project.domain.response.ReservationResponseDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

@Mapper(componentModel = "spring",imports = {LocalDate.class})
public abstract class ReservationMapper {

    @Mapping(target = "modifiedDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "subTotal", expression = "java(total(reservationRequest))")
    @Mapping(target = "reservationStatus", defaultValue = "NEW")
    @Mapping(target = "created", expression  = "java(LocalDate.now())")
    @Mapping(target = "reservationDetails.lineTotal", expression = "java(calculatorLineTotal(reservationRequest))")
    public abstract Reservation map(ReservationRequest reservationRequest);

    public abstract ReservationResponseDetail mapToReservationDto(Reservation reservation);

    public abstract ReservationResponse mapToReservationDto1(Reservation reservation);

    public abstract void updateReservation(ReservationRequest reservationRequest, @MappingTarget Reservation reservation);


    public double total(ReservationRequest reservationRequest){
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        reservationRequest.getReservationDetails().forEach(detail->{
            if(detail.getUnitPriceDiscount() == 0){
                total.set(detail.getUnitPrice() * detail.getQuantity() * reservationRequest.getNight());
            }else{
                total.set(detail.getUnitPriceDiscount() * detail.getQuantity() * reservationRequest.getNight());
            }
        });
        return total.get();
    }

    public double calculatorLineTotal(ReservationRequest reservationRequest){
        AtomicReference<Double> lineTotal = new AtomicReference<>((double) 0);
        reservationRequest.getReservationDetails().forEach(detail->{
            lineTotal.set(detail.getUnitPrice() * detail.getQuantity());
        });
        return lineTotal.get();
    }
}
