package backend.persist.models;

import backend.persist.entity.WorkPlace;
import lombok.Data;

@Data
public class ThreadingModel {
    private PersonModel personModel;
    private TradeUnionModel tradeUnionModel;
    private WorkPlace workPlace;

    public ThreadingModel(PersonModel personModel, TradeUnionModel tradeUnionModel, WorkPlace workPlace) {
        setPersonModel(personModel);
        setTradeUnionModel(tradeUnionModel);
        setWorkPlace(workPlace);
    }


}
