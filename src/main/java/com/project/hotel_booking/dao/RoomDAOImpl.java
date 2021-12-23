package com.project.hotel_booking.dao;

import com.project.hotel_booking.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class RoomDAOImpl implements RoomDAO{

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Room> findAll() {
        Session session=entityManager.unwrap(Session.class);

        return session.createQuery("from Room",Room.class).getResultList();
    }

    @Override
    public void save(Room room) {
        Session session=entityManager.unwrap(Session.class);
        session.clear();
        session.saveOrUpdate(room);


    }

    @Override
    public Room findById(int roomId) {
        Session session=entityManager.unwrap(Session.class);
         return session.get(Room.class,roomId);
    }

    @Override
    public boolean isRoomNumberExists(Room room) {
        Session session=entityManager.unwrap(Session.class);
        Query<Room> query=session.createQuery("from Room where room_no=:roomNumber",Room.class);
        query.setParameter("roomNumber",room.getRoomNo());

        if(query.getResultList().isEmpty() || query.getSingleResult().getId()==room.getId())
        {
            return false;
        }

        return true;
    }

    @Override
    public void deleteById(int roomId) {
        Session session=entityManager.unwrap(Session.class);
        Room room=session.get(Room.class,roomId);
        session.delete(room);
    }
}
