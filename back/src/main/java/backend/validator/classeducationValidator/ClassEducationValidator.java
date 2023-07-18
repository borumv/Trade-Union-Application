package backend.validator.classeducationValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

/**
 * Custom validator for the ClassEducation annotation.
 * <p>
 * Validates if the value is one of the predefined authors.
 *
 * @author Boris Vlasevsky
 */
public class ClassEducationValidator implements ConstraintValidator<ClassEducation, String> {

    private List<String> authors = Arrays.asList(
            "Базовое", "Высшее", "Среднее специальное", "Неоконченное высшее",
            "Профессионально техническое", "Среднее", "Не указано", "Неоконченное высшее",
            "Неполное базовое", "Неполное среднее", "Отсутствует", "Неоконченное высшее"
    );

    /**
     * Checks if the value is valid by comparing it with the predefined authors.
     *
     * @param value   the value to validate
     * @param context the constraint validator context
     * @return true if the value is valid, false otherwise
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return authors.contains(value);
    }
}