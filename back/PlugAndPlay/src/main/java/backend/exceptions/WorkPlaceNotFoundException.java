package backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class WorkPlaceNotFoundException extends RuntimeException {
    public WorkPlaceNotFoundException(int id) {
        super("WorkPlace id not found : " + id);
    }
}
