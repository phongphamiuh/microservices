package com.hotel.project.dto;

import com.hotel.project.model.entity.StarType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequest {
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String district;
    @NotNull
    private String city;

    @NotNull
    private StarType star;

    @NotNull
    private String description;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @NotNull
    private Instant checkin;

    @NotNull
    private Instant checkout;

    @NotNull
    private String logoUrl;

    private Long userId;

    private String categoryName;

    private Date modifiedDate;

}
