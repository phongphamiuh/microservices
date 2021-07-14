package com.hotel.project.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDetailRequest {
    private int quantity;
    private double unitPrice;
    private double unitPriceDiscount;
    private Long roomId;
}
