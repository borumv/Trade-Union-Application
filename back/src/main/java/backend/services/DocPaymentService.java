package backend.services;

import backend.controllers.BaseRestController;
import backend.exceptions.DocPaymentNotFoundException;
import backend.persist.entity.DocPayment;
import backend.persist.entity.QuantityPayTradeUnion;
import backend.persist.repositories.DocPaymentRepo;
import backend.persist.repositories.QuantityPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service class for DocPayment-related operations.
 *
 * @author Boris Vlasevsky
 */
@Service
public class DocPaymentService extends BaseRestController {

    @Autowired
    DocPaymentRepo paymentRepo;

    @Autowired
    QuantityPayment qantityPaymentRepo;

    /**
     * Retrieves a list of all DocPayment objects.
     *
     * @return a list of all DocPayment objects
     */
    public List<DocPayment> getAll() {

        return paymentRepo.findAll();
    }

    /**
     * Retrieves a list of QuantityPayTradeUnion objects representing the quantity of payments per trade union.
     *
     * @return a list of QuantityPayTradeUnion objects
     */
    public List<QuantityPayTradeUnion> getPayCountOfTradeUnion() {

        return qantityPaymentRepo.getQuantityPay();
    }

    /**
     * Retrieves a list of QuantityPayTradeUnion objects representing the count of payments that are paid and not paid.
     *
     * @return a list of QuantityPayTradeUnion objects
     */
    public List<QuantityPayTradeUnion> getDontPaied() {

        return qantityPaymentRepo.getDontPaied();
    }

    /**
     * Retrieves a DocPayment object by the specified ID.
     *
     * @param id the ID of the DocPayment object to retrieve
     * @return the retrieved DocPayment object
     * @throws DocPaymentNotFoundException if no DocPayment object is found with the specified ID
     */
    public DocPayment findById(int id) throws DocPaymentNotFoundException {

        return paymentRepo.findById(id)
                .orElseThrow(() -> new DocPaymentNotFoundException(id));
    }

    /**
     * Creates a new DocPayment object.
     *
     * @param docPayment the DocPayment object to create
     * @return the created DocPayment object
     */
    public DocPayment createDocPayment(DocPayment docPayment) {

        return paymentRepo.save(docPayment);
    }

    /**
     * Updates the endPay date of a DocPayment object with the specified payment ID.
     *
     * @param paymentId the ID of the payment to update
     * @return the updated DocPayment object
     * @throws DocPaymentNotFoundException if no DocPayment object is found with the specified payment ID
     */
    public DocPayment update(int paymentId) throws DocPaymentNotFoundException {

        DocPayment docPayment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new DocPaymentNotFoundException(paymentId));
        docPayment.setEndPay(new Date());
        docPayment.setId(paymentId);
        return paymentRepo.save(docPayment);
    }

    /**
     * Deletes a DocPayment object with the specified ID.
     *
     * @param id the ID of the payment to delete
     * @return the deleted DocPayment object
     */
    public DocPayment deletePayment(int id) {

        DocPayment docPayment = new DocPayment();
        paymentRepo.deleteById(id);
        return docPayment;
    }

    /**
     * Deletes all DocPayment objects associated with the specified person ID.
     *
     * @param id the person ID
     */
    public void deleteByPersonId(int id) {

        paymentRepo.deleteByPersonId(id);
    }
}