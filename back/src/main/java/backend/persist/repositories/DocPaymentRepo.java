package backend.persist.repositories;

import backend.persist.entity.DocPayment;
import backend.persist.entity.QuantityPayTradeUnion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocPaymentRepo extends CrudRepository<DocPayment, Integer> {

    /**
     * Retrieves a DocPayment object by the person ID.
     *
     * @param id the ID of the person
     * @return the corresponding DocPayment object
     */
    DocPayment findDocPaymentByPersonId(int id);

    /**
     * Retrieves all DocPayment objects from the repository.
     *
     * @return a list of all DocPayment objects
     */
    List<DocPayment> findAll();

    /**
     * Retrieves a list of QuantityPayTradeUnion objects representing the quantity of payments per trade union.
     *
     * @return a list of QuantityPayTradeUnion objects
     */
    @Query(value = "SELECT name, COUNT(doc_payment.id) FROM doc_payment INNER JOIN class_org ON doc_payment.org_id = class_org.id GROUP BY name", nativeQuery = true)
    List<QuantityPayTradeUnion> getQuantityPay();

    /**
     * Deletes DocPayment objects based on the person ID.
     *
     * @param id the ID of the person
     */
    void deleteByPersonId(int id);
}
