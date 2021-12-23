package com.project.hotel_booking.service;

import com.project.hotel_booking.dao.AvailabilityDAO;
import com.project.hotel_booking.dto.BookingDTO;
import com.project.hotel_booking.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService{

    @Autowired
    private AvailabilityDAO availabilityDAO;


    @Override
    @Transactional
    public List<Room> getAvailableRoom(BookingDTO booking) {
        return availabilityDAO.getAvailableRoom(booking);
    }
}
