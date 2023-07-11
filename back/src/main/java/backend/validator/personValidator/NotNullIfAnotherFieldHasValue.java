package backend.validator.personValidator;
import jakarta.persistence.Inheritance;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = NotNullIfAnFieldHasValueValidator.class)
@Documented
@Inheritance
public @interface NotNullIfAnotherFieldHasValue {

    String message() default "Not null if another field has value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
//    String first();
//    String second();

}

