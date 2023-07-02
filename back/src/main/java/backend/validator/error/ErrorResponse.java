package backend.validator.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private String message;
    private Integer status;
    private ZonedDateTime timeStamp;
    private String errorCode;
    private String path;
}
