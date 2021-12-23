package com.project.hotel_booking.entity;

import com.project.hotel_booking.Constraints.UniqueRoomNumber;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "room")
@NoArgsConstructor
@UniqueRoomNumber(message = "Room Number already exists")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "room_no")
    @Min(value = 1,message = "Enter Valid Room Number")
    private int roomNo;

    @NonNull
    @Min(value = 1,message = "Enter Atleast 1 Adult")
    @Column(name = "adult_capacity")
    private int adultCapacity;

    @NonNull
    @Min(value = 0,message = "Enter valid number!")
    @Column(name = "children_capacity")
    private int childrenCapacity;

    @NonNull
    @Min(value = 0,message = "Price Required!")
    @Column(name = "price")
    private int price;


}
