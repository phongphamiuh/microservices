package com.hotel.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Category implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private Long categoryId;

    @Column(name="name")
    private String name;

    public Category(String name){
        this.name=name;
    }
}
