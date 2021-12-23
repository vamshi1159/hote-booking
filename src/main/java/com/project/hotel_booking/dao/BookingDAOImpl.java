package com.project.hotel_booking.dao;

import com.project.hotel_booking.dto.BookingDTO;
import com.project.hotel_booking.entity.Booking;
import com.project.hotel_booking.entity.Status;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookingDAOImpl implements BookingDAO{

    @Autowired
    private EntityManager entityManager;
    @Override
    public List<BookingDTO> findAll() {
        Session session=entityManager.unwrap(Session.class);

        List<Booking> bookings= session.createQuery("from Booking",Booking.class).getResultList();

        return bookings.stream().map(this::convertDataToDTO).collect(Collectors.toList());
    }
    private BookingDTO convertDataToDTO(Booking booking)
    {
        BookingDTO bookingDTO=new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setFirstName(booking.getFirstName());
        bookingDTO.setLastName(booking.getLastName());
        bookingDTO.setEmail(booking.getEmail());
        bookingDTO.setAge(booking.getAge());
        bookingDTO.setNoOfAdults(booking.getNoOfAdults());
        bookingDTO.setNoOfChildren(bookingDTO.getNoOfChildren());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setRoomNo(booking.getRoom().getRoomNo());
        bookingDTO.setPrice(booking.getRoom().getPrice());
        bookingDTO.setStatus(booking.getStatus().getLabel());

        return bookingDTO;
    }

    @Override
    public BookingDTO findById(int bookingId) {
        Session session=entityManager.unwrap(Session.class);
        Booking booking=session.get(Booking.class,bookingId);

        if(booking==null)
        {
            return null;
        }
        return this.convertDataToDTO(booking);
    }

    @Override
    public List<BookingDTO> checkIns() {
        List<BookingDTO>bookingDTOS=this.findAll();

        return bookingDTOS.stream().filter(bookingDTO ->
           bookingDTO.getCheckInDate().isEqual(LocalDate.now()) && bookingDTO.getStatus().equalsIgnoreCase("BOOKED")
                ).collect(Collectors.toList());
    }
    @Override
    public List<BookingDTO> checkOuts() {
        List<BookingDTO>bookingDTOS=this.findAll();

        return bookingDTOS.stream().filter(bookingDTO ->
                bookingDTO.getCheckOutDate().isEqual(LocalDate.now()) && bookingDTO.getStatus().equalsIgnoreCase("CHECKIN")
        ).collect(Collectors.toList());
    }

    @Override
    public void save(Booking booking) {

        Session session=entityManager.unwrap(Session.class);
        session.saveOrUpdate(booking);
    }

    @Override
    public void deleteById(int bookingId) {

        Session session=entityManager.unwrap(Session.class);
        Booking booking=session.get(Booking.class,bookingId);
        session.delete(booking);
    }

    @Override
    public void updateStatus(int bookingId, int statusId) {

        Session session=entityManager.unwrap(Session.class);
        Booking booking=session.get(Booking.class,bookingId);
        Status status=session.get(Status.class,statusId);
        booking.setStatus(status);
    }
}
