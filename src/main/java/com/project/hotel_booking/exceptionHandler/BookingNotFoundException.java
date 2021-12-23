package com.project.hotel_booking.exceptionHandler;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(String message) {
        super(message);
    }
}
