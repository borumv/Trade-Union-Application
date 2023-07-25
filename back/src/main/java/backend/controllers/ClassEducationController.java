
package backend.controllers;

import backend.persist.entity.ClassEducation;
import backend.persist.models.ClassEducationModel;
import backend.persist.models.PersonModel;
import backend.services.ClassEducationService;
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

/**
 * The ClassEducationController class handles requests related to class education.
 * It provides endpoints for retrieving class education information and managing class participants.
 *
 * @author Boris Vlasevsky
 */
@RestController
@RequestMapping("/api/education")
@Validated
@CrossOrigin(origins = {"cors.allow"})
public class ClassEducationController {


    @Autowired
    ClassEducationService classEducationService;

    Logger logger = LoggerFactory.getLogger(ClassEducationController.class);

    /**
     * Retrieves a list of persons who are taking the specified type of education.
     *
     * @param typeEducationId the ID of the type of education.
     * @return a list of PersonModel objects representing the persons taking the education.
     */
    @PreAuthorize("hasAuthority('tradeunion:edit')")
    @GetMapping("/{typeEducationId}/allpersons")
    public List<PersonModel> getAllWhoTakeThisTypeEducation(
            @PathVariable
            @Min(value = 1, message = "{classeducation.id.size.error}") @Max(10) Integer typeEducationId) {


        return classEducationService.getAllWhoTakeThisTypeEducation(typeEducationId).stream().map(PersonModel::toModel).collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all class education records.
     *
     * @return a list of ClassEducation objects representing the class education records.
     */
    @PreAuthorize("hasAuthority('tradeunion:read')")
    @GetMapping()
    public List<ClassEducation> findAll() {

        return classEducationService.getAllType();
    }

    /**
     * Retrieves the class education record with the specified ID.
     *
     * @param id the ID of the class education record.
     * @return a ClassEducationModel object representing the class education record.
     */
    @PreAuthorize("hasAuthority('tradeunion:read')")
    @GetMapping("/{id}")
    public ClassEducationModel findById(
            @PathVariable
            @Min(0) int id) {

        return ClassEducationModel.toModel(classEducationService.findById(id));
    }

}
