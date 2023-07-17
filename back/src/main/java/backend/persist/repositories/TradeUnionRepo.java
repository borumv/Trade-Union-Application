package backend.persist.repositories;
import backend.persist.entity.TradeUnion;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface TradeUnionRepo extends PagingAndSortingRepository<TradeUnion, Integer> {

    /**
     * Retrieves an Optional of TradeUnion object by the specified union ID.
     *
     * @param unionId the ID of the trade union
     * @return an Optional of TradeUnion object
     */
    Optional<TradeUnion> findById(int unionId);

    /**
     * Saves the specified TradeUnion object.
     *
     * @param tradeUnion the TradeUnion object to be saved
     * @return the saved TradeUnion object
     */
    TradeUnion save(TradeUnion tradeUnion);

    /**
     * Retrieves a list of all TradeUnion objects.
     *
     * @return a list of all TradeUnion objects
     */
    List<TradeUnion> findAll();

    /**
     * Deletes a TradeUnion object by the specified ID.
     *
     * @param id the ID of the trade union to be deleted
     */
    void deleteById(int id);

}
