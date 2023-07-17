
package backend.controllers;

import backend.persist.entity.DocPayment;
import backend.persist.entity.PersonEntity;
import backend.persist.entity.TradeUnion;
import backend.persist.models.PersonModel;
import backend.persist.models.TradeUnionModel;
import backend.services.TradeUnionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The TradeUnionController class handles requests related to trade unions.
 * It provides endpoints for retrieving, adding, updating, and deleting trade union information.
 */
@RestController
@RequestMapping("/api/union")
@CrossOrigin(origins = {"http://localhost:3000"})
public class TradeUnionController {

    @Autowired
    TradeUnionService tradeUnionService;
    Logger logger = LoggerFactory.getLogger(TradeUnionController.class);

    /**
     * Retrieves the trade union with the specified union ID.
     *
     * @param unionId the union ID of the trade union.
     * @return a TradeUnion object representing the trade union.
     */
    @GetMapping("/{unionId}")
    @PreAuthorize("hasAuthority('tradeunion:read')")
    public TradeUnion getById(
            @PathVariable
            int unionId) throws RuntimeException {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getById", a.getName(), "TradeUnionController");
        return tradeUnionService.getById(unionId);
    }

    /**
     * Retrieves all document payments associated with the trade union of the specified union ID.
     *
     * @param unionId the union ID of the trade union.
     * @return a list of DocPayment objects representing the document payments.
     */
    @GetMapping("/{unionId}/docpayments")
    @PreAuthorize("hasAuthority('tradeunion:read')")
    public List<DocPayment> getAllDocPayment(
            @PathVariable
            int unionId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getAllDocPayment", a.getName(), "TradeUnionController");
        return tradeUnionService.getAllDocPayments(unionId);
    }

    /**
     * Retrieves all active members associated with the trade union of the specified union ID and page number.
     *
     * @param unionId    the union ID of the trade union.
     * @param pageNumber the page number for pagination.
     * @return a list of PersonModel objects representing the active members.
     */
    @GetMapping("/{unionId}/members{pageNumber}")
    @PreAuthorize("hasAuthority('tradeunion:read')")
    public List<PersonModel> getAllMembers(
            @PathVariable
            int unionId,
            @PathVariable
            int pageNumber) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getAllMembers", a.getName(), "TradeUnionController");
        return tradeUnionService.getAllActiveMembers(unionId, PageRequest.of(pageNumber, 15)).stream().map(PersonModel::toModel).collect(Collectors.toList());
    }

    /**
     * Retrieves all active members associated with the trade union of the specified union ID.
     *
     * @param unionId the union ID of the trade union.
     * @return a list of PersonModel objects representing the active members.
     */
    @GetMapping("/{unionId}/members")
    @PreAuthorize("hasAuthority('tradeunion:read')")
    public List<PersonModel> getAllMembers(
            @PathVariable
            int unionId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getAllMembers", a.getName(), "TradeUnionController");
        return tradeUnionService.getAllActiveWithoutPageable(unionId).stream().map(PersonModel::toModel).collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all trade unions.
     *
     * @return a list of TradeUnion objects representing the trade unions.
     */
    @PreAuthorize("hasAuthority('tradeunion:read')")
    @GetMapping()
    public List<TradeUnion> getAllTradeUnion() {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getAllTradeUnion", a.getName(), "TradeUnionController");
        return tradeUnionService.getAllNaturalTradeUnions();
    }

    /**
     * Deletes the trade union with the specified union ID.
     *
     * @param unionId the union ID of the trade union to be deleted.
     * @return a TradeUnion object representing the deleted trade union.
     */
    @PreAuthorize("hasAuthority('tradeunion:edit')")
    @DeleteMapping("/{unionId}")
    public TradeUnion delete(
            @PathVariable
            int unionId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: delete", a.getName(), "TradeUnionController");
        return tradeUnionService.deleteTradeUnion(unionId);
    }

    /**
     * Adds a new trade union.
     *
     * @param tradeUnionModel the TradeUnionModel object representing the trade union to be added.
     * @param bindingResult   the binding result for validation.
     * @return a TradeUnion object representing the added trade union.
     */
    @PreAuthorize("hasAuthority('tradeunion:edit')")
    @PostMapping()
    public TradeUnion add(
            @RequestBody
            TradeUnionModel tradeUnionModel, BindingResult bindingResult) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: add", a.getName(), "TradeUnionController");
        return tradeUnionService.createTradeUnion(tradeUnionModel);
    }

    /**
     * Updates the trade union with the specified ID.
     *
     * @param id   the ID of the trade union to be updated.
     * @param item the updated TradeUnion object.
     * @return a TradeUnion object representing the updated trade union.
     */
    @PreAuthorize("hasAuthority('tradeunion:edit')")
    @PutMapping("/{id}")
    public TradeUnion update(
            @PathVariable
            int id,
            @RequestBody
            TradeUnion item) {

        return tradeUnionService.update(id, item);

    }

}
