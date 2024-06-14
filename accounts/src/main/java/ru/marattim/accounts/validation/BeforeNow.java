package ru.marattim.accounts.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BeforeNowValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeforeNow {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int minYears() default 0;

    int maxYears() default Integer.MAX_VALUE;
}
