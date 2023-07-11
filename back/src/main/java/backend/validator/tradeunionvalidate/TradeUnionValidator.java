package backend.validator.tradeunionvalidate;

import backend.persist.entity.TradeUnion;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
