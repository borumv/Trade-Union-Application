package backend.validator.tradeunionvalidate;

import backend.persist.entity.TradeUnion;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * TradeUnionValidator is a custom validator that validates a TradeUnion object.
 * It checks if the nameUnion field is empty and adds an error to the Errors object if it is.
 */
public class TradeUnionValidator implements Validator {

    /**
     * Determines if the validator supports the given class.
     *
     * @param clazz the class to check for support
     * @return true if the validator supports the class, false otherwise
     */
    @Override
    public boolean supports(Class<?> clazz) {

        return TradeUnion.class.equals(clazz);
    }

    /**
     * Validates the given object and adds any validation errors to the Errors object.
     *
     * @param obj    the object to validate
     * @param errors the Errors object to add validation errors to
     */
    @Override
    public void validate(Object obj, Errors errors) {

        TradeUnion tradeUnion = (TradeUnion) obj;
        if (tradeUnion.getNameUnion().equals("")) {
            errors.rejectValue("nameUnion", "tradeunion.name.size");
        }
    }

    /**
     * Creates a bean of TradeUnionValidator to be used for validating TradeUnion objects before creation.
     *
     * @return an instance of TradeUnionValidator
     */
    @Bean
    public TradeUnionValidator beforeCreateTradeUnionValidator() {

        return new TradeUnionValidator();
    }
}
