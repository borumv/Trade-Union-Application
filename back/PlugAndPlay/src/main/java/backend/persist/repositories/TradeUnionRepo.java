package backend.persist.repositories;

import backend.persist.entity.PersonEntity;
import backend.persist.entity.TradeUnion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TradeUnionRepo extends PagingAndSortingRepository<TradeUnion, Integer> {
//    public TradeUnion findById(int unionId);
    public List<TradeUnion>findAll();



}
