package com.project.hotel_booking.exceptionHandler;

public class RoomNotFoundException extends RuntimeException{
    public RoomNotFoundException(String message) {
        super(message);
    }
}
