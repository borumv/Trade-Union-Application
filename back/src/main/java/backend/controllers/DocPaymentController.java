
package backend.controllers;

import backend.persist.entity.DocPayment;
import backend.persist.models.DocPaymentModel;
import backend.persist.entity.QuantityPayTradeUnion;
import backend.services.DocPaymentService;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The DocPaymentController class handles requests related to document payments.
 * It provides endpoints for retrieving, deleting, and updating document payment information.
 *
 * @author Boris Vlasevsky
 */
@RestController
@RequestMapping("/api/docpayments")
@CrossOrigin(origins = {"http://localhost:3000"})
public class DocPaymentController {

    @Autowired
    private DocPaymentService docPaymentService;
    Logger logger = LoggerFactory.getLogger(DocPaymentController.class);

    /**
     * Retrieves a list of all document payments.
     *
     * @return a list of DocPaymentModel objects representing the document payments.
     */
    @PreAuthorize("hasAuthority('tradeunion:read')")
    @GetMapping()
    public List<DocPaymentModel> getAll() {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getAll", a.getName(), "DocPaymentController");
        return docPaymentService.getAll().stream().map(DocPaymentModel::toModel).collect(Collectors.toList());
    }

    /**
     * Retrieves the document payment with the specified payment ID.
     *
     * @param payId the payment ID.
     * @return a DocPayment object representing the document payment.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/{payId}")
    public DocPayment getById(
            @PathVariable
            @Min(value = 1, message = "{person.id.size.error}") int payId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getById", a.getName(), "DocPaymentControllers");
        return docPaymentService.findById(payId);
    }

    /**
     * Deletes the document payment with the specified payment ID.
     *
     * @param payId the payment ID.
     * @return a DocPayment object representing the deleted document payment.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @DeleteMapping("/{payId}")
    public DocPayment delete(
            @PathVariable
            @Min(value = 1, message = "{person.id.size.error}") int payId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: delete", a.getName(), "DocPaymentControllers");
        return docPaymentService.deletePayment(payId);
    }

    /**
     * Updates the document payment with the specified payment ID.
     *
     * @param payId the payment ID.
     * @return a DocPayment object representing the updated document payment.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @PutMapping("/{payId}")
    public DocPayment update(
            @PathVariable
            int payId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: update", a.getName(), "DocPaymentControllers");
        return docPaymentService.update(payId);
    }

    /**
     * Retrieves the quantity of payments per trade union.
     *
     * @return a list of QuantityPayTradeUnion objects representing the payment counts per trade union.
     */
    @PreAuthorize("hasAuthority('tradeunion:read')")
    @GetMapping("/count")
    public List<QuantityPayTradeUnion> geQuantityPay() {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: geQuantityPay", a.getName(), "DocPaymentController");
        return docPaymentService.getPayCountOfTradeUnion();
    }

    /**
     * Retrieves the quantity of payments that are not yet made per trade union.
     *
     * @return a list of QuantityPayTradeUnion objects representing the counts of unpaid payments per trade union.
     */
    @PreAuthorize("hasAuthority('tradeunion:read')")
    @GetMapping("/count_statistic")
    public List<QuantityPayTradeUnion> getCountNotPaied() {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getDontPaied", a.getName(), "DocPaymentController");
        return docPaymentService.getDontPaied();
    }

}
