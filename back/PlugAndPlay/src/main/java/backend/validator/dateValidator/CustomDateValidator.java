package backend.validator.dateValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateValidator implements ConstraintValidator<CustomDate,Date> {


        @Override
        public boolean isValid(Date date, ConstraintValidatorContext context) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
            sdf.format(date);
//            String dateString = sdf.format(date);
           return DateValidatorSimpleDateFormat.isValid(sdf.format(date));
        }

    }

