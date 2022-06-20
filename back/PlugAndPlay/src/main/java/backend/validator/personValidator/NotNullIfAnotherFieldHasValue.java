package backend.validator.personValidator;

import backend.persist.entity.PersonEntity;

import javax.persistence.Inheritance;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;

import static
        java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Target;


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

