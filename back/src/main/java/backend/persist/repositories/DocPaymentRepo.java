package backend.persist.repositories;

import backend.persist.entity.DocPayment;
import backend.persist.entity.PersonEntity;
import backend.persist.models.QuantityPayTradeUnion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.print.Doc;
import java.util.List;

public interface DocPaymentRepo extends CrudRepository<DocPayment,Integer> {
    DocPayment findDocPaymentByPersonId(int id);
    List<DocPayment> findAll();

    @Query(value = "SELECT name, COUNT (doc_payment.id) FROM doc_payment INNER JOIN class_org ON doc_payment.org_id = class_org.id GROUP BY name",  nativeQuery = true )
    List<QuantityPayTradeUnion>getQuantityPay();
}
