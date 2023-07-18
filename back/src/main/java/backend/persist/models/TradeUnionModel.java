package backend.persist.models;

import backend.persist.entity.TradeUnion;
import lombok.Builder;
import lombok.Data;

/**
 * Model class representing a TradeUnion.
 * <p>
 * This class includes attributes for the ID, nameUnion, city, and address.
 *
 * @author Boris Vlasevsky
 */
@Data
@Builder
public class TradeUnionModel {

    private Integer id;
    private String nameUnion;
    private String city;
    private String address;

    /**
     * Converts a TradeUnion object to a TradeUnionModel object using the builder pattern.
     *
     * @param tradeUnion the TradeUnion object to be converted
     * @return the corresponding TradeUnionModel object
     */
    public static TradeUnionModel toModel(TradeUnion tradeUnion) {

        return TradeUnionModel.builder()
                .id(tradeUnion.getId())
                .nameUnion(tradeUnion.getNameUnion())
                .city(tradeUnion.getCity())
                .address(tradeUnion.getAddress())
                .build();
    }
}