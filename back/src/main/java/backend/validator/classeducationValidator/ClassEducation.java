package backend.validator.classeducationValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Custom validation annotation for checking if a class education value is allowed.
 * <p>
 * The value should be one of the predefined authors.
 *
 * @author Boris Vlasevsky
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ClassEducationValidator.class)
@Documented
public @interface ClassEducation {

    /**
     * Returns the error message template.
     *
     * @return the error message template
     */
    String message() default "ClassEducation is not allowed.";

    /**
     * Returns the groups to which this constraint belongs.
     *
     * @return the groups
     */
    Class<?>[] groups() default {};

    /**
     * Returns the payload associated with this constraint.
     *
     * @return the payload
     */
    Class<? extends Payload>[] payload() default {};
}