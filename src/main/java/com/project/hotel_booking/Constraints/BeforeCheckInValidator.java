package com.project.hotel_booking.Constraints;

import com.project.hotel_booking.dto.BookingDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class BeforeCheckInValidator implements ConstraintValidator<BeforeCheckIn, BookingDTO> {



    @Override
    public void initialize(BeforeCheckIn constraintAnnotation) {

    }

    @Override
    public boolean isValid(BookingDTO bookingDTO, ConstraintValidatorContext constraintValidatorContext) {

        if(bookingDTO.getCheckInDate()!=null && bookingDTO.getCheckOutDate()!=null)
        {
            return bookingDTO.getCheckInDate().isBefore(bookingDTO.getCheckOutDate());
        }
        return false;
    }
}
