package com.project.hotel_booking.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "label",unique = true)
    private String label;
}
