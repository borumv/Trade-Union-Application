package backend.persist.models;

import backend.persist.entity.PersonEntity;
import backend.persist.entity.TradeUnion;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeUnionModel {

    private Integer id;
    private String nameUnion;
    private String city;
    private String address;

    public static TradeUnionModel toModel(TradeUnion tradeUnion) {

        return TradeUnionModel.builder()
                .id(tradeUnion.getId())
                .address(tradeUnion.getAddress())
                .city(tradeUnion.getCity()).build();
    }
}
