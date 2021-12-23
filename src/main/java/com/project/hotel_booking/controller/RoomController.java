package com.project.hotel_booking.controller;

import com.project.hotel_booking.entity.Room;
import com.project.hotel_booking.exceptionHandler.RoomNotFoundException;
import com.project.hotel_booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/list")
    public String getList(Model model)
    {
        model.addAttribute("rooms",roomService.findAll());
        return "list-rooms";
    }

    @GetMapping("/create")
    public String form(Model model)
    {
        model.addAttribute("room",new Room());

        return "room-form";
    }
    @PostMapping("/save")
    public String saveRoom(@Valid @ModelAttribute("room") Room room, BindingResult bindingResult)
    {

        if(bindingResult.hasErrors())
        {
            return "room-form";
        }


        roomService.save(room);

        return "redirect:/rooms/list";

    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("roomId") int roomId, Model model)
    {

        Room room=roomService.findById(roomId);
        if(room==null)
        {
            throw new RoomNotFoundException("room with id "+roomId+" not found");
        }
        model.addAttribute("room",room);
        return "room-form";
    }

    @GetMapping("/delete")
    public String deleteRoom(@RequestParam("roomId") int roomId)
    {
        Room room=roomService.findById(roomId);
        if(room==null)
        {
            throw new RoomNotFoundException("room with id "+roomId+" not found");
        }
        roomService.deleteById(roomId);
        return "redirect:/room/list";
    }
}
