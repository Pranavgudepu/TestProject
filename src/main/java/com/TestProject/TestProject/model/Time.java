package com.TestProject.TestProject.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "time")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime time;
    private LocalTime clockin;
    private LocalTime clockout;
    private String duration;
    private String comments;


}
