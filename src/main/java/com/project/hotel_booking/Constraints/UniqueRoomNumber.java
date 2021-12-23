package com.project.hotel_booking.Constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueRoomNumberConstraint.class)
public @interface UniqueRoomNumber {
    String message() default "room already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
