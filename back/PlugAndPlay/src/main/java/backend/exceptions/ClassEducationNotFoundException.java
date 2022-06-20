package backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ClassEducationNotFoundException extends RuntimeException {

    public ClassEducationNotFoundException(int id) {
        super("ClassEducation id not found : " + id);
    }
}