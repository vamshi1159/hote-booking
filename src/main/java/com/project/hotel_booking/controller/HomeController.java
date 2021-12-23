package com.project.hotel_booking.controller;

import com.project.hotel_booking.dto.BookingDTO;
import com.project.hotel_booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
   private BookingService bookingService;
    @RequestMapping("/")
    public String home(Model model)
    {
        List<BookingDTO> checkIns=bookingService.checkIns();
        List<BookingDTO> checkOuts=bookingService.checkOuts();
        model.addAttribute("checkIns",checkIns);
        model.addAttribute("checkOuts",checkOuts);
        return "home-page";
    }

    @RequestMapping("/checkIn")
    public String checkIn(@RequestParam("bookingId") int bookingId,Model model)
    {
        System.out.println("before");
        bookingService.updateStatus(bookingId,2);
        System.out.println("after");
        return "redirect:/";
    }
    @RequestMapping("/checkOut")
    public String checkOut(@RequestParam("bookingId") int bookingId,Model model)
    {
        System.out.println("before");
        bookingService.updateStatus(bookingId,3);
        System.out.println("after");
        return "redirect:/";
    }

    @GetMapping("/showLogin")
    public String showLoginPage()
    {
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied()
    {
        return "access-denied";
    }

}
