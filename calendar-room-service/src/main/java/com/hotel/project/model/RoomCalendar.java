package com.hotel.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomCalendar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_calendar_id")
    private Long roomCalendarId;
    private int quantity;
    private double price;
    private Long roomId;
    private Long discountPrice;
    private int discountPercent;
    private Instant modifiedDate;

    @ManyToOne
    @JoinColumn(name="available_date_id",referencedColumnName = "available_date_id")
    private AvailableDate availableDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomCalendar that = (RoomCalendar) o;
        return Objects.equals(roomId, that.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId);
    }
}
