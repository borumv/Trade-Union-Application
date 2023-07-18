package backend.validator.dateValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Utility class for validating a date string with a specific format using SimpleDateFormat.
 *
 * @author Boris Vlasevsky
 */
public class DateValidatorSimpleDateFormat {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");

    /**
     * Validates if a date string is valid according to the specified format.
     *
     * @param date the date string to validate
     * @return true if the date string is valid, false otherwise
     */
    public static boolean isValid(final String date) {

        boolean valid = false;
        try {
            sdf.parse(date);
            sdf.setLenient(false); // Set strict mode - check for valid days, leap years
            valid = true;
        } catch (ParseException e) {
            e.printStackTrace();
            valid = false;
        }
        return valid;
    }
}