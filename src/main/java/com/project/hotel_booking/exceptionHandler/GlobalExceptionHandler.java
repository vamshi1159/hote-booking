package com.project.hotel_booking.exceptionHandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ModelAndView handleRoomNotFound(RoomNotFoundException exception)
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("value",exception.getMessage());
        return modelAndView;
    }
    @ExceptionHandler
    public ModelAndView handleBookingNotFound(BookingNotFoundException exception)
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("value",exception.getMessage());
        return modelAndView;
    }
}
