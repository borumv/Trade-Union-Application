package backend.controllers;

import backend.persist.entity.DocPayment;
import backend.persist.models.DocPaymentModel;
import backend.persist.models.QuantityPayTradeUnion;
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

@RestController
@RequestMapping("/api/docpayments")
@CrossOrigin(origins = {"http://localhost:3000"})
public class DocPaymentController {

    @Autowired
    private DocPaymentService docPaymentService;
    Logger logger = LoggerFactory.getLogger(DocPaymentController.class);

    @PreAuthorize("hasAuthority('tradeunion:read')")
    @GetMapping()
    public List<DocPaymentModel> getAll(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getAll",  a.getName(), "DocPaymentController");
        return docPaymentService.getAll().stream().map(DocPaymentModel::toModel).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/{payId}")
    public DocPayment getById(@PathVariable @Min(value = 1, message = "{person.id.size.error}") int payId) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getById",  a.getName(), "DocPaymentControllers");
        return docPaymentService.findById(payId);
    }

    @PreAuthorize("hasAuthority('persons:read')")
    @DeleteMapping("/{payId}")
    public DocPayment Delete(@PathVariable @Min(value = 1, message = "{person.id.size.error}") int payId) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: delete",  a.getName(), "DocPaymentControllers");
        return docPaymentService.deletePayment(payId);
//        return PersonModel.toModel(personEntity);
    }

    @PreAuthorize("hasAuthority('persons:read')")
    @PutMapping("/{payId}")
    public DocPayment update(@PathVariable int payId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: update",  a.getName(), "DocPaymentControllers");
        return docPaymentService.update(payId);
//        return PersonModel.toModel(personEntity);
    }

    @PreAuthorize("hasAuthority('tradeunion:read')")
    @GetMapping("/count")
    public List<QuantityPayTradeUnion> geQuantityPay(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: geQuantityPay",  a.getName(), "DocPaymentController");
        return docPaymentService.getPayCountOfTradeUnion();
    }

    @PreAuthorize("hasAuthority('tradeunion:read')")
    @GetMapping("/count_statistic")
    public List<QuantityPayTradeUnion> getCountNotPaied(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getDontPaied",  a.getName(), "DocPaymentController");
        return docPaymentService.getDontPaied();
    }

}
