package backend.persist.models;

import backend.persist.entity.WorkPlace;
import lombok.Data;

/**
 * Model class representing threading information.
 * <p>
 * This class includes a PersonModel object, a TradeUnionModel object, and a WorkPlace object.
 *
 * @author Boris Vlasevsky
 */
@Data
public class ThreadingModel {

    private PersonModel personModel;
    private TradeUnionModel tradeUnionModel;
    private WorkPlace workPlace;

    /**
     * Constructs a new ThreadingModel object with the specified PersonModel, TradeUnionModel, and WorkPlace.
     *
     * @param personModel     the PersonModel object representing the person information
     * @param tradeUnionModel the TradeUnionModel object representing the trade union information
     * @param workPlace       the WorkPlace object representing the work place information
     */
    public ThreadingModel(PersonModel personModel, TradeUnionModel tradeUnionModel, WorkPlace workPlace) {

        setPersonModel(personModel);
        setTradeUnionModel(tradeUnionModel);
        setWorkPlace(workPlace);
    }

}