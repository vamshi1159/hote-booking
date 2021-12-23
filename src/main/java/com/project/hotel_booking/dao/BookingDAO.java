package com.project.hotel_booking.dao;

import com.project.hotel_booking.dto.BookingDTO;
import com.project.hotel_booking.entity.Booking;

import java.util.List;

public interface BookingDAO {

    public List<BookingDTO> findAll();

    public BookingDTO findById(int bookingId);

    public List<BookingDTO> checkIns();

    public List<BookingDTO> checkOuts();

    public void save(Booking booking);

    public void deleteById(int bookingId);

    void updateStatus(int bookingId, int statusId);
}