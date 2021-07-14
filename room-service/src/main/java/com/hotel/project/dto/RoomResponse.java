package com.hotel.project.dto;

import com.hotel.project.entity.Facilities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponse {
    private Long roomId;
    private String title;
    private int acreage;
    private String bedTypeName;
    private int guest;
    private int childrent;
    private String description;
    private String safeBox;
    private boolean bookable;
    private String pageUrl;
    private String photo;
    private Set<Facilities> facilities;
}
