package backend.validator.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationError {
    public String message, path, type = "error";
}