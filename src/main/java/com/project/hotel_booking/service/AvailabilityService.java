package com.project.hotel_booking.service;

import com.project.hotel_booking.dto.BookingDTO;
import com.project.hotel_booking.entity.Room;

import java.util.List;

public interface AvailabilityService {

    public List<Room> getAvailableRoom(BookingDTO booking);
}
