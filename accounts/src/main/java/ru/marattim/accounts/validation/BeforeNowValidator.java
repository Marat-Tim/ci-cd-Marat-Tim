package ru.marattim.accounts.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class BeforeNowValidator implements ConstraintValidator<BeforeNow, LocalDate> {
    private BeforeNow beforeNow;

    @Override
    public void initialize(BeforeNow beforeNow) {
        this.beforeNow = beforeNow;
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext context) {
        int yearsBetween = localDate.until(LocalDate.now()).getYears();
        if (!localDate.isBefore(LocalDate.now()) ||
                yearsBetween < beforeNow.minYears() ||
                yearsBetween > beforeNow.maxYears()) {
            String message = context.getDefaultConstraintMessageTemplate();
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            message.replace("${minYears}", Integer.toString(beforeNow.minYears()))
                                    .replace("${maxYears}", Integer.toString(beforeNow.maxYears())))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
