package backend.validator.personValidator;
import org.springframework.data.domain.DomainEvents;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullIfAnFieldHasValueValidator implements ConstraintValidator<NotNullIfAnotherFieldHasValue, Object> {

    @Override
    public void initialize(NotNullIfAnotherFieldHasValue constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof FirstSecondFieldInterface) {
            Object targetValue = new Object();
//            (FirstSecondFieldInterface) targetValue = (FirstSecondFieldInterface) value;
            String firstFieldName = ((FirstSecondFieldInterface) value).getFirstField();
            String secondFieldName = ((FirstSecondFieldInterface) value).getSecondField();
            return !(secondFieldName != null & firstFieldName.equals("string"));
        }
        return true;
    }
}

//    @Override
//    public void initialize(NotNullIfAnotherFieldHasValue
//                                   constraintAnnotation) {
//        firstFieldName = constraintAnnotation.first();
//        secondFieldName = constraintAnnotation.second();
//    }

//    @SneakyThrows
//    @Override
//    public boolean isValid(Object value, final
//    ConstraintValidatorContext context) {
////        return !secondObj.equals("") && firstObj.equals("string");
//               final String firstObj = BeanUtils.getProperty(value, firstFieldName);
//                final String secondObj = BeanUtils.getProperty(value, secondFieldName);
//                if (secondObj != null & firstObj.equals("string")) {
//                    return false;
//                }
//
//            return true;
//        }
//    }


//    @SneakyThrows
//    @Override
//    public boolean isValid(Object value, final
//    ConstraintValidatorContext context) {
////        return !secondObj.equals("") && firstObj.equals("string");
//
//        Method[] methods = value.getClass().getDeclaredMethods();
//        for (Method method : methods) {
//            if (method.getName().equals("getFirstField")) {
//                firstFieldName = (String) method.getDefaultValue();
//            if (method.getName().equals("getSecondField")) {
//                secondFieldName = (String) method.getDefaultValue();
//            }
//        }
//        return !(secondFieldName != null & firstFieldName.equals("string"));
//    }
//}


//            }
