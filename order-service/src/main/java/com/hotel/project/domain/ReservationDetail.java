package com.hotel.project.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderdetail_id")
    private Long orderDetailId;
    private int quantity;
    private double unitPrice;
    private double unitPriceDiscount;
    private double lineTotal;
    private Long roomId;
    private Instant modifiedDate;
    @PrePersist
    protected void onCreate() {
        modifiedDate = Instant.now();
    }
    @PreUpdate
    protected void onUpdate(){
        modifiedDate=Instant.now();
    }
}
