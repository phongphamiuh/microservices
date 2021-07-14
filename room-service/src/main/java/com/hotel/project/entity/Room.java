package com.hotel.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private Long roomId;
    private String title;
    private int acreage;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "bed_type_id", referencedColumnName = "bed_type_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private BedType bedType;

    private int guest;
    private int childrent;
    private String description;
    private String safeBox;
    private boolean bookable;
    private String pageUrl;
    private String photo;
    private Instant modifiedDate;
    private Long hotelId;

//    @JsonIgnore
    @ManyToMany(targetEntity = Facilities.class)
 //   @JoinTable(name="room_facilities",joinColumns = {@JoinColumn(name="room_id")},inverseJoinColumns = {@JoinColumn(name="facilities_id")})
    private List<Facilities> facilities;

    public void addFacilities(Facilities facilities){
        this.facilities.add(facilities);
    }
}
