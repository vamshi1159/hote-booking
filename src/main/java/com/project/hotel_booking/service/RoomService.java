package com.project.hotel_booking.service;

import com.project.hotel_booking.entity.Room;

import java.util.List;

public interface RoomService {
    public List<Room> findAll();
    public void save(Room room);
    public Room findById(int roomId);
    public boolean isRoomNumberExists(Room room);
    public void deleteById(int roomId);
}
