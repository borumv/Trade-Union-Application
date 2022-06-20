package backend.validator.classeducationValidator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ClassEducationValidator.class)
@Documented
public @interface ClassEducation {

    String message() default "ClassEducation is not allowed.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
