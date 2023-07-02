package backend.persist.models;
import lombok.Data;
import java.util.List;


@Data
public class JsonModel {
    List<TradeUnionModel> result;
    int count;
    public static JsonModel toJsonModel(List<TradeUnionModel> model){
        JsonModel jsonModel = new JsonModel();
        jsonModel.setResult(model);
        jsonModel.setCount(model.size());
        return jsonModel;
    }

}
