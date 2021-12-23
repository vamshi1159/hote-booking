package com.project.hotel_booking.dao;

import com.project.hotel_booking.dto.BookingDTO;
import com.project.hotel_booking.entity.Room;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class AvailabilityDAOImpl implements AvailabilityDAO{

    @Autowired
   private EntityManager entityManager;


    @Override
    public List<Room> getAvailableRoom(BookingDTO booking) {
        Session session=entityManager.unwrap(Session.class);
        String bookedRooms="(select b.room.id from Room r inner join Booking b on r.id=b.room.id " +
                            "where ((:checkin between b.checkInDate and b.checkOutDate) or (:checkout between " +
                             "b.checkInDate and b.checkOutDate)) and (b.status.id!=4 and b.status.id!=3))";
        String rooms="from Room r where ((r.adultCapacity>=:adult and r.childrenCapacity>=:child) or " +
                        "(r.adultCapacity>=:adult+:child) )and ";
        String notIn="r.id not in";
        Query<Room> roomQuery=session.createQuery(rooms+notIn+bookedRooms,Room.class);


       roomQuery.setParameter("adult",booking.getNoOfAdults());
       roomQuery.setParameter("child",booking.getNoOfChildren());
       roomQuery.setParameter("checkin",booking.getCheckInDate());
       roomQuery.setParameter("checkout",booking.getCheckOutDate());
       return roomQuery.getResultList();
    }
}
