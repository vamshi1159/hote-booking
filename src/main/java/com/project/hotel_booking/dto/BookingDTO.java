package com.project.hotel_booking.dto;

import com.project.hotel_booking.Constraints.BeforeCheckIn;
import com.project.hotel_booking.entity.Booking;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@BeforeCheckIn(message = "checkout must be after checkin")
public class BookingDTO {

    private int id;

    @NonNull
    @NotNull(message = "Required!")
    private String firstName;

    @NonNull
    @NotNull(message = "Required!")
    private String lastName;

    @NonNull
    @NotNull(message = "Required!")
    private String email;

    @NonNull
    @NotNull(message = "Required!")
    @Min(value = 18,message = "Age must be greater than 18")
    @Max(value = 100,message = "Enter valid Age!")
    private int age;

    @NonNull
    @NotNull(message = "Required!")
    @Min(value = 1,message = "Enter Atleast 1 Adult!")
    private int noOfAdults;

    @NonNull
    @NotNull(message = "Required!")
    @Min(value = 0,message = "Enter valid Number!")
    private int noOfChildren;

    @NonNull
    @FutureOrPresent
    private LocalDate checkInDate;

    @NonNull
    @FutureOrPresent
    private LocalDate checkOutDate;

    private int roomNo;

    private int price;

    private String status;
    public Booking convertToBooking()
    {
        Booking booking=new Booking();
        booking.setFirstName(this.firstName);
        booking.setLastName(this.lastName);
        booking.setEmail(this.email);
        booking.setAge(this.age);
        booking.setNoOfAdults(this.noOfAdults);
        booking.setNoOfChildren(this.noOfChildren);
        booking.setCheckInDate(this.checkInDate);
        booking.setCheckOutDate(this.checkOutDate);
        return booking;
    }

}
