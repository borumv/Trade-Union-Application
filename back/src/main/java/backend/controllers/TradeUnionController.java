package backend.controllers;

import backend.persist.entity.DocPayment;
import backend.persist.entity.PersonEntity;
import backend.persist.entity.TradeUnion;
import backend.persist.models.PersonModel;
import backend.persist.models.TradeUnionModel;
import backend.services.TradeUnionService;
import io.swagger.annotations.ApiImplicitParam;
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

@RestController
@RequestMapping("/api/union")
@CrossOrigin(origins = {"http://localhost:3000"})
public class TradeUnionController{
    @Autowired
    TradeUnionService tradeUnionService;
    Logger logger = LoggerFactory.getLogger(TradeUnionController.class);

    @GetMapping("/{unionId}")
    @PreAuthorize("hasAuthority('tradeunion:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    public TradeUnion getById(@PathVariable int unionId) throws RuntimeException{
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getById",  a.getName(), "TradeUnionController");
        return tradeUnionService.getById(unionId);
    }

    @GetMapping("/{unionId}/docpayments")
    @PreAuthorize("hasAuthority('tradeunion:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    public List<DocPayment> getAllDocPayment(@PathVariable int unionId){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getAllDocPayment",  a.getName(), "TradeUnionController");
        return tradeUnionService.getAllDocPayments(unionId);
    }

    @GetMapping("/{unionId}/members{pageNumber}")
    @PreAuthorize("hasAuthority('tradeunion:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    public List<PersonModel> getAllMembers(@PathVariable int unionId, @PathVariable int pageNumber){
        List<PersonEntity> personEntityList = tradeUnionService.getAllActiveMembers(unionId, PageRequest.of(1,3));
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getAllMembers",  a.getName(), "TradeUnionController");
        return tradeUnionService.getAllActiveMembers(unionId, PageRequest.of(pageNumber,15)).stream().map(PersonModel::toModel).collect(Collectors.toList());}


    @GetMapping("/{unionId}/members")
    @PreAuthorize("hasAuthority('tradeunion:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    public List<PersonModel> getAllMembers(@PathVariable int unionId){
        List<PersonEntity> personEntityList = tradeUnionService.getAllActiveMembers(unionId, PageRequest.of(1,3));
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getAllMembers",  a.getName(), "TradeUnionController");
        return tradeUnionService.getAllActiveWithoutPageable(unionId).stream().map(PersonModel::toModel).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('tradeunion:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @GetMapping()
    public List<TradeUnion> getAllTradeUnion(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getAllTradeUnion",  a.getName(), "TradeUnionController");
        return tradeUnionService.getAllNaturalTradeUnions();
    }


    @PreAuthorize("hasAuthority('tradeunion:edit')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @DeleteMapping("/{unionId}")
    public TradeUnion delete(@PathVariable int unionId){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: delete",  a.getName(), "TradeUnionController");
        return tradeUnionService.deleteTradeUnion(unionId);
    }

    @PreAuthorize("hasAuthority('tradeunion:edit')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @PostMapping()
    public TradeUnion add(@RequestBody TradeUnionModel tradeUnionModel , BindingResult bindingResult){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: add",  a.getName(), "TradeUnionController");
        return tradeUnionService.createTradeUnion(tradeUnionModel);
    }

    @PreAuthorize("hasAuthority('tradeunion:edit')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @PutMapping("/{id}")
    public TradeUnion update(@PathVariable int id, @RequestBody TradeUnion item) {
       return tradeUnionService.update(id, item);

    }
}
