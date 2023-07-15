package backend.services;

import backend.controllers.BaseRestController;
import backend.exceptions.TradeUnionNotFoundExeption;
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

    public List<DocPayment> getAllDocPayments(int unionId) {

        Query q = em.createNativeQuery("SELECT\n" +
                                               "\tdoc_payment.*\n" +
                                               "FROM\n" +
                                               "\tdoc_payment\n" +
                                               "WHERE\n" +
                                               "\tdoc_payment.org_id = ?1", "DocPaymentMapping");
        q.setParameter(1, unionId);
        var resultList = q.getResultList();
        System.out.println();
        return resultList;
    }

    public List<PersonEntity> getAllActiveMembers(int unionId, Pageable pageable) {

        return personRepo.getAllActiveMembers(unionId, pageable);

    }

    public List<PersonEntity> getAllActiveWithoutPageable(int unionId) {

        return personRepo.getAllActiveWithoutPageable(unionId);

    }

    public List<TradeUnionModel> getAllTradeUnions() {

        List<TradeUnion> list = tradeUnionRepo.findAll();
        return list.stream().map(TradeUnionModel::toModel).collect(Collectors.toList());
    }

    public List<TradeUnion> getAllNaturalTradeUnions() {

        return tradeUnionRepo.findAll();
    }

    public TradeUnion createTradeUnion(TradeUnionModel model) {

        TradeUnion tradeUnion = new TradeUnion();
        tradeUnion.setNameUnion(model.getNameUnion());
        tradeUnion.setAddress(model.getAddress());
        tradeUnion.setCity(model.getCity());
        tradeUnion.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        return tradeUnionRepo.save(tradeUnion);
    }

    public TradeUnion deleteTradeUnion(int id) {

        TradeUnion tradeUnion = new TradeUnion();
        tradeUnionRepo.deleteById(id);
        return tradeUnion;
    }

    public TradeUnion getById(int id) {

        return tradeUnionRepo.findById(id).orElseThrow(() -> new TradeUnionNotFoundExeption(id));
    }

    public TradeUnion update(int id, TradeUnion tradeUnion) {

        TradeUnion tradeUnion1 = getById(id);
        merge(tradeUnion1, tradeUnion);
        tradeUnion1.setId(id);
        tradeUnion1.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        return tradeUnionRepo.save(tradeUnion1);
    }
}
