package backend.validator.personValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * NotNullIfAnFieldHasValueValidator is a custom validator that checks if a field is not null when another field has a specific value.
 * It implements the ConstraintValidator interface and is annotated with @ConstraintValidator to indicate that it is a validator for the NotNullIfAnotherFieldHasValue constraint.
 *
 * @author Boris
 */
public class NotNullIfAnFieldHasValueValidator implements ConstraintValidator<NotNullIfAnotherFieldHasValue, Object> {

    @Override
    public void initialize(NotNullIfAnotherFieldHasValue constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (value instanceof FirstSecondFieldInterface) {
            String firstFieldName = ( (FirstSecondFieldInterface) value ).getFirstField();
            String secondFieldName = ( (FirstSecondFieldInterface) value ).getSecondField();
            return ! ( secondFieldName != null && firstFieldName.equals("string") );
        }
        return true;
    }
}
