package com.hotel.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Facilities implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="facilities_id")
    private Long facilitiesId;
    private String name;
    private String icon;
 //   @ManyToMany(mappedBy = "facilities")
 //   private List<Room> hotels;

    public Facilities(String name,String icon){
        this.name=name;
        this.icon=icon;
    }
}
