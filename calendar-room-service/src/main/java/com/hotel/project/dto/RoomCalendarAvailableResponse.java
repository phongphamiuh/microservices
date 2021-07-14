package com.hotel.project.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomCalendarAvailableResponse {
    private Long roomCalendarId;
    private int quantity;
    private double price;
    private Long roomId;
    private Long discountPrice;
    private int discountPercent;
}
