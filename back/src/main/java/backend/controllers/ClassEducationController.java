package backend.controllers;

import backend.persist.entity.ClassEducation;
import backend.persist.models.ClassEducationModel;
import backend.persist.models.PersonModel;
import backend.services.ClassEducationService;
import io.swagger.annotations.ApiImplicitParam;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/education")
@Validated
@CrossOrigin(origins = {"http://localhost:3000"})
public class ClassEducationController {

    @Autowired
    ClassEducationService classEducationService;

    Logger logger = LoggerFactory.getLogger(ClassEducationController.class);

    @PreAuthorize("hasAuthority('tradeunion:edit')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @GetMapping("/{typeEducationId}/allpersons")
    public List<PersonModel> getAllWhoTakeThisTypeEducation(@PathVariable @Min(value = 1, message = "{classeducation.id.size.error}") @Max(10) Integer typeEducationId) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class:{} Action: getAllWhoTakeThisTypeEducation",  a.getName(), "ClassEducationController");
        return classEducationService.getAllWhoTakeThisTypeEducation(typeEducationId).stream().map(PersonModel::toModel).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('tradeunion:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @GetMapping()
    public List<ClassEducation> findAll() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: findAll",  a.getName(), "ClassEducationController");
        return classEducationService.getAllType();
    }

    @PreAuthorize("hasAuthority('tradeunion:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @GetMapping("/{id}")
    public ClassEducationModel findById(@PathVariable @Min(0) int id){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: findById",  a.getName(), "ClassEducationController");
        return ClassEducationModel.toModel(classEducationService.findById(id));

    }

}
