package com.project.hotel_booking.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "booking")
public class Booking  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "age")
    private int age;

    @NonNull
    @Column(name = "no_of_adults")
    private int noOfAdults;

    @Column(name = "no_of_children")
    private int noOfChildren;

    @NonNull
    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @NonNull
    @Column(name = "check_out_date")
    private LocalDate checkOutDate;


    @NonNull
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "room_id")
    private Room room;

    @NonNull
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "status_id")
    private  Status status;
}
