package backend.exceptions;

import backend.controllers.PersonControllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

 Exception class indicating that a specific validation logic is not supported by the class.
 This exception is typically thrown when attempting to use an unsupported validation logic with the class.
 */
public class NotWorkingWithThisValidationLogicException extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(NotWorkingWithThisValidationLogicException.class);

    /**
     Constructs a new NotWorkingWithThisValidationLogicException.
     This exception is thrown when the class does not support the given validation logic.
     */
    public NotWorkingWithThisValidationLogicException() {
        super("This Class does not support this Validation logic");
        logger.info("Invalid use of validation");
    }
}