package backend.exceptions;

import backend.controllers.PersonControllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotWorkingWithThisValidationLogicException extends Exception {
    Logger logger = LoggerFactory.getLogger(NotWorkingWithThisValidationLogicException.class);

    public NotWorkingWithThisValidationLogicException(){
        super("This Class not supported this Validation logic");
        logger.info("invalid use validation");
    }
}
