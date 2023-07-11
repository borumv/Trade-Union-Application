package backend.validator.classeducationValidator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class ClassEducationValidator implements ConstraintValidator<ClassEducation,String> {
    List<String> authors = Arrays.asList("Базовое", "Высшее", "Среднее специальное", "Неоконченное высшее",
            "Профессионально техническое", "Среднее", "Не указано", "Неоконченное высшее",
            "Неполное базовое", "Неполное среднее", "Отсутствует", "Неоконченное высшее");


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return authors.contains(value);

    }
}
