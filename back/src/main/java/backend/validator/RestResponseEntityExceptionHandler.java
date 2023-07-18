package backend.validator;

import backend.exceptions.*;
import backend.security.AuthenticationException;
import backend.validator.error.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.Principal;
import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<List<CustomErrorResponse>> authenticationException(AuthenticationException a) {
        List<CustomErrorResponse> customErrorResponses = new ArrayList<>();
        a.getErrors()
                .forEach(error -> {
                    customErrorResponses.add(CustomErrorResponse.builder()
                                                     .message(error.getMessage())
                                                     .status(HttpStatus.FORBIDDEN.value())
                                                     .path(error.getPath())
                                                     .error("Authentication error")
                                                     .build());
                });
        return new ResponseEntity<>(customErrorResponses, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({PersonNotFoundException.class,
            ClassEducationNotFoundException.class,
            DocMemberNotFoundException.class,
            WorkPlaceNotFoundException.class,
            TradeUnionNotFoundException.class,
            UserNameNotFoundException.class,
            ErrorNewPasswordException.class,}
    )
    public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request, Principal principal) {

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setError("Not found Exception");
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        errors.setMessage(ex.getMessage());
        errors.setPath(ex.getMessage().split(" ")[0]);
        logger.error("UserId: {}. {}", principal.getName(), errors.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ValidationProblem onConstraintValidationException(ConstraintViolationException e, Principal principal) {

        ValidationProblem problem = new ValidationProblem();
        problem.setMessage(e.getMessage());
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            problem.getErrors().add(new ValidationError(violation.getMessage(), propertyPath.substring(propertyPath.lastIndexOf('.') + 1), "error"));
        }
        logger.error("UserId: {}. {}", principal.getName(), problem.getErrors());
        return problem;
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Exception exception = new Exception(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.error("UserId: {}. {}", a.getName(), errors.stream().toArray());
        return this.createResponseEntity(HttpStatus.BAD_REQUEST, exception, request, errors);
    }

    private ResponseEntity<Object> createResponseEntity(HttpStatus httpStatus, Exception ex, WebRequest request, List<ObjectError> errors) {

        MainCustomErrorResponse mainCustomErrorResponse = new MainCustomErrorResponse();
        for (ObjectError fieldError : errors) {
            if (fieldError instanceof FieldError) {
                CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                        .status(httpStatus.value())
                        .error(httpStatus.getReasonPhrase())
                        .message(fieldError.getDefaultMessage())
                        .path(( (FieldError) fieldError ).getField())
                        .build();
                mainCustomErrorResponse.getErrors().add(customErrorResponse);
            } else {
                CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                        .status(httpStatus.value())
                        .error(httpStatus.getReasonPhrase())
                        .message(fieldError.getDefaultMessage())
                        .path(fieldError.getObjectName())
                        .build();
                mainCustomErrorResponse.getErrors().add(customErrorResponse);
            }
        }
        return handleExceptionInternal(ex, mainCustomErrorResponse, new HttpHeaders(), httpStatus, request);
    }
}
