package com.hotel.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class BedType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bed_type_id")
    private Long id;
    private String name;
}
