package com.project.hotel_booking.dao;

import com.project.hotel_booking.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomDAO {

    public List<Room> findAll();
    public void save(Room room);
    public Room findById(int roomId);
    public boolean isRoomNumberExists(Room room);
    public void deleteById(int roomId);
}
