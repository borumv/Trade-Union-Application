package backend.persist.repositories;

import backend.persist.entity.QuantityPayTradeUnion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuantityPayment extends CrudRepository<QuantityPayTradeUnion, String> {

    /**
     * Retrieves a list of QuantityPayTradeUnion objects representing the quantity of payments per trade union.
     *
     * @return a list of QuantityPayTradeUnion objects
     */
    @Query(value = "SELECT name, COUNT (doc_payment.id) FROM doc_payment INNER JOIN class_org ON doc_payment.org_id = class_org.id GROUP BY name", nativeQuery = true)
    List<QuantityPayTradeUnion> getQuantityPay();

    /**
     * Retrieves a list of QuantityPayTradeUnion objects representing the count of paid and unpaid payments.
     *
     * @return a list of QuantityPayTradeUnion objects
     */
    @Query(value = "\n" +
            "select 'Paied' as name, count(id) AS count\n" +
            "from doc_payment \n" +
            "where finished is not null\n" +
            "UNION ALL\n" +
            "select 'Not Paied' as name, count(id) AS count\n" +
            "from doc_payment \n" +
            "where finished IS NULL\n", nativeQuery = true
    )
    List<QuantityPayTradeUnion> getDontPaied();

}
