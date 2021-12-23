package com.project.hotel_booking.service;

import com.project.hotel_booking.dao.BookingDAO;
import com.project.hotel_booking.dto.BookingDTO;
import com.project.hotel_booking.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingDAO bookingDAO;
    @Override
    @Transactional
    public List<BookingDTO> findAll() {
        return bookingDAO.findAll();
    }

    @Override
    @Transactional
    public BookingDTO findById(int bookingId) {
        return bookingDAO.findById(bookingId);
    }

    @Override
    public List<BookingDTO> checkIns() {
        return bookingDAO.checkIns();
    }

    @Override
    public List<BookingDTO> checkOuts() {
        return bookingDAO.checkOuts();
    }

    @Override
    @Transactional
    public void save(Booking booking) {

        bookingDAO.save(booking);
    }

    @Override
    @Transactional
    public void deleteById(int bookingId) {
        bookingDAO.deleteById(bookingId);
    }

    @Override
    @Transactional
    public void updateStatus(int bookingId, int statusId) {
        bookingDAO.updateStatus(bookingId,statusId);
    }
}
