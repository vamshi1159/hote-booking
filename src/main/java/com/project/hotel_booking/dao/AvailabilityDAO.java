package com.project.hotel_booking.dao;

import com.project.hotel_booking.dto.BookingDTO;
import com.project.hotel_booking.entity.Room;

import java.util.List;

public interface AvailabilityDAO {
    public List<Room> getAvailableRoom(BookingDTO booking);
}

