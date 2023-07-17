package backend.services;

import backend.controllers.BaseRestController;
import backend.exceptions.TradeUnionNotFoundException;
import backend.persist.entity.DocPayment;
import backend.persist.entity.PersonEntity;
import backend.persist.entity.TradeUnion;
import backend.persist.models.TradeUnionModel;
import backend.persist.repositories.DocTradeUnionRepo;
import backend.persist.repositories.PersonRepo;
import backend.persist.repositories.TradeUnionRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeUnionService extends BaseRestController {

    @Autowired
    TradeUnionRepo tradeUnionRepo;

    @Autowired
    PersonRepo personRepo;

    @Autowired
    DocTradeUnionRepo docTradeUnionRepo;

    @PersistenceContext
    private EntityManager em;

    /**
     * Retrieves a list of DocPayment objects for a trade union.
     *
     * @param unionId the ID of the trade union
     * @return a list of DocPayment objects
     */
    public List<DocPayment> getAllDocPayments(int unionId) {

        Query q = em.createNativeQuery("SELECT\n" +
                                               "\tdoc_payment.*\n" +
                                               "FROM\n" +
                                               "\tdoc_payment\n" +
                                               "WHERE\n" +
                                               "\tdoc_payment.org_id = ?1", "DocPaymentMapping");
        q.setParameter(1, unionId);
        return q.getResultList();
    }

    /**
     * Retrieves a paginated list of active members for a trade union.
     *
     * @param unionId  the ID of the trade union
     * @param pageable the pageable object specifying the page number and size
     * @return a list of active members
     */
    public List<PersonEntity> getAllActiveMembers(int unionId, Pageable pageable) {

        return personRepo.getAllActiveMembers(unionId, pageable);
    }

    /**
     * Retrieves a list of all active members for a trade union without pagination.
     *
     * @param unionId the ID of the trade union
     * @return a list of active members
     */
    public List<PersonEntity> getAllActiveWithoutPageable(int unionId) {

        return personRepo.getAllActiveWithoutPageable(unionId);
    }

    /**
     * Retrieves a list of all trade unions.
     *
     * @return a list of trade union models
     */
    public List<TradeUnionModel> getAllTradeUnions() {

        List<TradeUnion> list = tradeUnionRepo.findAll();
        return list.stream().map(TradeUnionModel::toModel).collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all natural trade unions.
     *
     * @return a list of trade unions
     */
    public List<TradeUnion> getAllNaturalTradeUnions() {

        return tradeUnionRepo.findAll();
    }

    /**
     * Creates a new trade union.
     *
     * @param model the trade union model containing the details of the trade union to create
     * @return the created trade union
     */
    public TradeUnion createTradeUnion(TradeUnionModel model) {

        TradeUnion tradeUnion = new TradeUnion();
        tradeUnion.setNameUnion(model.getNameUnion());
        tradeUnion.setAddress(model.getAddress());
        tradeUnion.setCity(model.getCity());
        tradeUnion.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        return tradeUnionRepo.save(tradeUnion);
    }

    /**
     * Deletes a trade union with the specified ID.
     *
     * @param id the ID of the trade union to delete
     * @return the deleted trade union
     */
    public TradeUnion deleteTradeUnion(int id) {

        TradeUnion tradeUnion = new TradeUnion();
        tradeUnionRepo.deleteById(id);
        return tradeUnion;
    }

    /**
     * Retrieves a trade union by ID.
     *
     * @param id the ID of the trade union to retrieve
     * @return the retrieved trade union
     * @throws TradeUnionNotFoundException if no trade union is found with the specified ID
     */
    public TradeUnion getById(int id) throws TradeUnionNotFoundException {

        return tradeUnionRepo.findById(id).orElseThrow(() -> new TradeUnionNotFoundException(id));
    }

    /**
     * Updates the details of a trade union.
     *
     * @param id         the ID of the trade union to update
     * @param tradeUnion the updated trade union details
     * @return the updated trade union
     */
    public TradeUnion update(int id, TradeUnion tradeUnion) {

        TradeUnion tradeUnion1 = getById(id);
        merge(tradeUnion1, tradeUnion);
        tradeUnion1.setId(id);
        tradeUnion1.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        return tradeUnionRepo.save(tradeUnion1);
    }

}
