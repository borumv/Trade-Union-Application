package backend.persist.models;

import backend.persist.entity.PersonEntity;
import backend.persist.entity.TradeUnion;
import lombok.Data;

@Data
public class TradeUnionModel {
    private Integer id;
    private String nameUnion;
    private String city;
    private String address;

    public static TradeUnionModel toModel(TradeUnion tradeUnion){
            TradeUnionModel model = new TradeUnionModel();
            model.setId(tradeUnion.getId());
            model.setAddress(tradeUnion.getAddress());
            model.setNameUnion(tradeUnion.getNameUnion());
            model.setCity(tradeUnion.getCity());
        return model;
    }
}
