package backend;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ValidateUtil {
    public static List<String> createErrorsMessage(BindingResult bindingResult){
        List<FieldError> list = bindingResult.getFieldErrors();
        List<String> message = new ArrayList<>();
        for(FieldError fieldError:list){
            message.add("@" + fieldError.getField().toUpperCase() + ":" + fieldError.getDefaultMessage());
        }
        return message;
    }
}
