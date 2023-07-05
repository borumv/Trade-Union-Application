package backend.validator;

import backend.validator.error.CustomErrorResponse;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class MainCustomErrorResponse {
    private List<CustomErrorResponse> errors = new ArrayList<>();
}
