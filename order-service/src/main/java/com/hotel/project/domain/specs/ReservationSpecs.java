package com.hotel.project.domain.specs;

import com.hotel.project.domain.Reservation;
import com.hotel.project.domain.ReservationStatus;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;

public final class ReservationSpecs {

    public static Specification<Reservation> withHotelId(Long hotelId) {
        return (root, query, cb) -> {
            if (hotelId == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.equal(root.get("hotelId"), hotelId);
        };
    }

    public static Specification<Reservation> withCheckIn(LocalDate start,LocalDate end) {
        return (root, query, cb) -> {
            if (start == null) {
                return cb.isTrue(cb.literal(true));
            }
            if (end == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.between(root.get("checkin"), start,end);
        };
    }

    public static Specification<Reservation> withReservationStatus(ReservationStatus reservationStatus) {
        return (root, query, cb) -> {
            if (reservationStatus == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.equal(root.get("reservationStatus"), reservationStatus);
        };
    }

    public static Specification<Reservation> withNameReservation(Object text) {
        return (root, query, cb) -> {
            if (text == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.like(root.get("nameReservation"), "%"+text+"%");
        };
    }

    public static Specification<Reservation> withCheckIn(Object text) {
        return (root, query, cb) -> {
            if (text == null) {
                return cb.isTrue(cb.literal(true));
            }
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            dateFormat.setLenient(false);
//            try {
//                dateFormat.parse(text.toString());
//            } catch (ParseException pe) {
//                return cb.isTrue(cb.literal(true));
//            }
//            LocalDate localDate=LocalDate.parse(text.toString());
            return cb.like(root.get("checkin"),"%"+text+"%");
        };
    }

    public static Specification<Reservation> withCheckOut(Object text) {
        return (root, query, cb) -> {
            if (text == null) {
                return cb.isTrue(cb.literal(true));
            }
            return cb.like(root.<String>get("checkout"),"%"+text+"%");
        };
    }

    public static Specification<Reservation> withTextReservationStatus(Object text) {
        return (root, query, cb) -> {
            if (text == null) {
                return cb.isTrue(cb.literal(true));
            }
            if(!Objects.equals(text,ReservationStatus.values())){
                return cb.isTrue(cb.literal(true));
            }

            return cb.like(root.get("reservationStatus"), "%"+text+"%");
        };
    }

}
