package backend.persist.models;
import lombok.Data;

import java.util.List;

/**
 * Model class representing a JSON response.
 * <p>
 * This class is used to encapsulate a list of TradeUnionModel objects and a count value for JSON serialization.
 *
 * @author Boris Vlasevsky
 */
@Data
public class JsonModel {

    List<TradeUnionModel> result;
    int count;

    /**
     * Converts a list of TradeUnionModel objects to a JsonModel object.
     *
     * @param model the list of TradeUnionModel objects to be converted
     * @return the corresponding JsonModel object
     */
    public static JsonModel toJsonModel(List<TradeUnionModel> model) {

        JsonModel jsonModel = new JsonModel();
        jsonModel.setResult(model);
        jsonModel.setCount(model.size());
        return jsonModel;
    }

}
