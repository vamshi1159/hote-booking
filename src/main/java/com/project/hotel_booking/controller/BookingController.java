package com.project.hotel_booking.controller;

import com.project.hotel_booking.dao.StatusDAO;
import com.project.hotel_booking.dto.BookingDTO;
import com.project.hotel_booking.entity.Booking;
import com.project.hotel_booking.entity.Room;
import com.project.hotel_booking.entity.Status;
import com.project.hotel_booking.exceptionHandler.BookingNotFoundException;
import com.project.hotel_booking.exceptionHandler.RoomNotFoundException;
import com.project.hotel_booking.service.AvailabilityService;
import com.project.hotel_booking.service.BookingService;
import com.project.hotel_booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;
    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private StatusDAO statusDAO;

    private static final String ATTRIBUTE="booking";
    @GetMapping("/list")
    public String list(Model model)
    {
        model.addAttribute("bookings",bookingService.findAll());
        return "list-bookings";
    }
    @GetMapping("/create")
    public String form(Model model)
    {
        model.addAttribute(ATTRIBUTE,new BookingDTO());

        return "booking-form";
    }
    @GetMapping("/book")
    public String book(HttpServletRequest request,@RequestParam("roomId") int roomId)
    {

        BookingDTO bookingDTO=(BookingDTO)request.getSession().getAttribute(ATTRIBUTE);
        Booking booking=bookingDTO.convertToBooking();
        System.out.println(booking);
        request.getSession().removeAttribute(ATTRIBUTE);
        Room room=roomService.findById(roomId);
        if(room==null)
        {
            throw new RoomNotFoundException("room with id "+roomId+" not found");
        }
        Status status=statusDAO.findById(1);
        booking.setRoom(room);
        booking.setStatus(status);
        bookingService.save(booking);
        return "redirect:/bookings/list";
    }

    @PostMapping("/availability")
    public String check(@Valid  @ModelAttribute("booking") BookingDTO booking, BindingResult bindingResult,Model model,  HttpServletRequest request)
    {
        System.out.println(booking);
        if(bindingResult.hasErrors())
        {
            return "booking-form";
        }
        System.out.println(booking);
         List<Room> rooms=  availabilityService.getAvailableRoom(booking);
        request.getSession().setAttribute(ATTRIBUTE,booking);
        model.addAttribute(ATTRIBUTE,booking);
        model.addAttribute("rooms",rooms);
        return "available-rooms";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("bookingId") int bookingId, Model model)
    {

        BookingDTO booking=bookingService.findById(bookingId);
        if(booking==null)
        {
            throw new BookingNotFoundException("booking with id "+bookingId+" not found");
        }

        model.addAttribute(ATTRIBUTE,booking);
        return "booking-form";
    }
    @GetMapping("/cancel")
    public String cancel(@RequestParam("bookingId") int bookingId,Model model)
    {
        BookingDTO booking=bookingService.findById(bookingId);
        if(booking==null)
        {
            throw new BookingNotFoundException("booking with id "+bookingId+" not found");
        }
        bookingService.updateStatus(bookingId,4);
        return "redirect:/bookings/list";
    }
}
