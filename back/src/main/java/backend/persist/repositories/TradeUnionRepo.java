package backend.persist.repositories;
import backend.persist.entity.TradeUnion;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface TradeUnionRepo extends PagingAndSortingRepository<TradeUnion, Integer> {
    Optional<TradeUnion> findById(int unionId);
    TradeUnion save(TradeUnion tradeUnion);
    List<TradeUnion>findAll();
    void deleteById(int id);

}
