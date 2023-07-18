package backend.validator.dateValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom validator for validating a Date value with a specific format.
 * <p>
 * Validates if the Date value matches the specified format.
 *
 * @author Boris Vlasevsky
 */
public class CustomDateValidator implements ConstraintValidator<CustomDate, Date> {

    /**
     * Validates if the Date value matches the specified format.
     *
     * @param date    the Date value to validate
     * @param context the constraint validator context
     * @return true if the Date value is valid, false otherwise
     */
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        sdf.format(date);
        return DateValidatorSimpleDateFormat.isValid(sdf.format(date));
    }
}