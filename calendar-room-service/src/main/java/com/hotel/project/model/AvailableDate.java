package com.hotel.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="available_date_id")
    private Long availableDateId;
    private LocalDate date;
    private int day;
    private int mounth;
    private int year;
    private String dayOfWeek;
    private Long hotelId;

    @OneToMany(mappedBy = "availableDate")
    @JsonIgnore
    public List<RoomCalendar> roomCalendars;

}
