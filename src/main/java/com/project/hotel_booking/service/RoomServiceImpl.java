package com.project.hotel_booking.service;

import com.project.hotel_booking.dao.RoomDAO;
import com.project.hotel_booking.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomDAO roomDAO;

    @Override
    @Transactional
    public List<Room> findAll() {
        return roomDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Room room) {
        roomDAO.save(room);
    }

    @Override
    @Transactional
    public Room findById(int roomId) {
        return roomDAO.findById(roomId);
    }

    @Override
    @Transactional
    public boolean isRoomNumberExists(Room room) {
        return roomDAO.isRoomNumberExists(room);
    }

    @Override
    @Transactional
    public void deleteById(int roomId) {
        roomDAO.deleteById(roomId);
    }
}
