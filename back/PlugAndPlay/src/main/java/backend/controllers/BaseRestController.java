package backend.controllers;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.beans.BeanUtils.copyProperties;

import java.lang.reflect.InvocationTargetException;

public class BaseRestController {

    public static final String OK_RESPONSE = "OK";
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String onBadRequestException(RuntimeException e) {
        return e.getMessage();
    }

    private final BeanUtilsBean cloner = new BeanUtilsBean()
    {
        @Override
        public void copyProperty(Object dest, String name, Object value) throws IllegalAccessException, InvocationTargetException {
            if (value != null)
                super.copyProperty(dest, name, value);
        }
    };

    public <T> void merge(T target, T source) {
        try {
            cloner.copyProperties(target, source);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
