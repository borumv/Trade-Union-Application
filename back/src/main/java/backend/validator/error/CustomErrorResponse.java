package backend.validator.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CustomErrorResponse {

    private int status;
    private String error;
    private String message;
    private String path;

    public CustomErrorResponse() {

    }
}
