
package backend.controllers;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.InvocationTargetException;

/**
 * The BaseRestController class includes a method for merging properties between objects.
 * @author Boris Vlasevsky
 */
public class BaseRestController {

    /**
     * Handles RuntimeExceptions and returns a bad request response with the exception message.
     *
     * @param e the RuntimeException that occurred.
     * @return the error message as a String.
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String onBadRequestException(RuntimeException e) {

        return e.getMessage();
    }

    private final BeanUtilsBean cloner = new BeanUtilsBean() {
        @Override
        public void copyProperty(Object dest, String name, Object value) throws IllegalAccessException, InvocationTargetException {

            if (value != null)
                super.copyProperty(dest, name, value);
        }
    };

    /**
     * Merges the properties from the source object to the target object.
     * Only non-null properties are merged.
     *
     * @param target the target object to merge into.
     * @param source the source object to merge from.
     * @param <T>    the type of the objects being merged.
     */
    public <T> void merge(T target, T source) {

        try {
            cloner.copyProperties(target, source);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
