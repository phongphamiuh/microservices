package com.hotel.project.dto;

import com.hotel.project.model.AvailableDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomCalendarRequest {
    private int quantity;
    private double price;
    private Long roomId;
    private Long discountPrice;
    private int discountPercent;
    private LocalDate date;
    private long hotelId;
}
