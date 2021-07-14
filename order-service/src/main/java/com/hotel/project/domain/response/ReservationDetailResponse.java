package com.hotel.project.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDetailResponse {
    private int quantity;
    private double unitPrice;
    private double unitPriceDiscount;
    private double lineTotal;

}
