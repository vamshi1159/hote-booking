package com.project.hotel_booking.Constraints;

import com.project.hotel_booking.entity.Room;
import com.project.hotel_booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueRoomNumberConstraint implements ConstraintValidator<UniqueRoomNumber, Room> {


    @Autowired
    private RoomService roomService;
    @Override
    public boolean isValid(Room room, ConstraintValidatorContext constraintValidatorContext) {

        if(room.getRoomNo()==0)return false;

        return !roomService.isRoomNumberExists(room);
    }
}
