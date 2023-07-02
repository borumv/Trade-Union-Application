package backend.validator.tradeunionvalidate;

import backend.persist.entity.TradeUnion;
import backend.persist.models.TradeUnionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class TradeUnionValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return TradeUnion.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        TradeUnion p = (TradeUnion) obj;
        if (p.getNameUnion().equals("")) {
            errors.rejectValue("nameUnion", "tradeunion.name.size");
        }
    }

    @Bean
    public TradeUnionValidator beforeCreateTradeUnionValidator() {
        return new TradeUnionValidator();
    }



}
