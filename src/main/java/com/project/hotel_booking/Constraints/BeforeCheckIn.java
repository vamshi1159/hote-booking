package com.project.hotel_booking.Constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BeforeCheckInValidator.class)
public @interface BeforeCheckIn
{
    String message() default "date mismatch";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


  
}
