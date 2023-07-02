package backend.validator;

import backend.validator.error.ValidationError;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ValidationProblem {
    private String message;
    private List<ValidationError> errors = new ArrayList<>();
}
