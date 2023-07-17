
package backend.controllers;

import backend.persist.entity.*;
import backend.persist.models.*;
import backend.services.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The PersonControllers class handles requests related to persons.
 * It provides endpoints for retrieving, adding, updating, and deleting person information.
 * @author Boris Vlasevsky
 */
@RestController
@Validated
@RequestMapping("/api/persons")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PersonControllers {

    @Autowired
    private PersonService personService;
    @Autowired
    private UserController userController;

    Logger logger = LoggerFactory.getLogger(PersonControllers.class);

    /**
     * Retrieves a list of persons with pagination.
     *
     * @param page the page number for pagination.
     * @return a list of AbstractModel objects representing the persons.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/all{page}")
    public List<AbstractModel> showPersonsList(
            @PathVariable
            int page) {

        UserWithAuthoritiesModel user = userController.getUser();
        switch (user.getRole()) {
            case ADMIN:
                List<PersonEntity> list = personService.getPagePersons(PageRequest.of(page, 3)).getContent();
                Authentication a = SecurityContextHolder.getContext().getAuthentication();
                logger.info("UserId: {}. Class: {} Action: showPersonsList", a.getName(), "PersonControllers");
            case USER:
                List<PersonEntity> list2 = personService.getPagePersons(PageRequest.of(page, 3)).getContent();
                List<AbstractModel> listModel2 = list2.stream().map(SlackPersonModel::toModel).collect(Collectors.toList());
                Authentication a1 = SecurityContextHolder.getContext().getAuthentication();
                logger.info("UserId: {}. Class: {} Action: showPersonsList", a1.getName(), "PersonControllers");
                return listModel2;
        }
        return null;
    }

    /**
     * Retrieves a list of all persons.
     *
     * @return a list of AbstractModel objects representing the persons.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping()
    public List<AbstractModel> showPersonsList() {

        List<AbstractModel> listModel = new ArrayList<>();
        UserModel user = userController.getActualUser();
        switch (user.getRole()) {
            case ADMIN:
                List<PersonEntity> list = personService.getAllPersons();
                listModel = list.stream().map(PersonModel::toModel).collect(Collectors.toList());
                Authentication a = SecurityContextHolder.getContext().getAuthentication();
                logger.info("UserId: {}. Class: {} Action: showPersonsList", a.getName(), "PersonControllers");
                return listModel;
            case USER:
                List<PersonEntity> list2 = personService.getAllPersons();
                listModel = list2.stream().map(SlackPersonModel::toModel).collect(Collectors.toList());
                Authentication a1 = SecurityContextHolder.getContext().getAuthentication();
                logger.info("UserId: {}. Class: {} Action: showPersonsList", a1.getName(), "PersonControllers");
                return listModel;
        }
        return listModel;
    }

    /**
     * Retrieves a list of persons whose name starts with the specified pattern.
     *
     * @param startWith the pattern to match the start of the firstName.
     * @return a list of PersonModel objects representing the matching persons.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/pattern={startWith}")
    public List<PersonModel> showPersonsListWhereNameStartWith(
            @PathVariable
            String startWith) {

        List<PersonEntity> list = personService.getAllPersonsWhereNameStartWith(startWith);
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: showPersonsListWhereNameStartWith", a.getName(), "PersonControllers");
        return list.stream().map(PersonModel::toModel).collect(Collectors.toList());
    }

    /**
     * Retrieves the person with the specified user ID.
     *
     * @param userId the user ID of the person.
     * @return a PersonEntity object representing the person.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/{userId}")
    public PersonEntity getById(
            @PathVariable
            @Min(value = 1, message = "{person.id.size.error}") int userId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getById", a.getName(), "PersonControllers");
        return personService.getPersonById(userId);
    }

    /**
     * Adds a new person.
     *
     * @param personEntity the PersonEntity object representing the person to be added.
     * @return a PersonModel object representing the added person.
     */
    @PreAuthorize("hasAuthority('persons:write')")
    @PostMapping()
    public PersonModel addPerson(
            @RequestBody
            @Valid PersonEntity personEntity) {

        logger.trace("addPerson method accessed");
        personEntity.setUpdate(Timestamp.valueOf(LocalDateTime.now()));
        personService.createPerson(personEntity);
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: addPerson", a.getName(), "PersonControllers");
        return PersonModel.toModel(personEntity);
    }

    /**
     * Deletes the person with the specified ID.
     *
     * @param id the ID of the person to be deleted.
     * @return a PersonEntity object representing the deleted person.
     */
    @PreAuthorize("hasAuthority('persons:delete')")
    @DeleteMapping("/{id}")
    public PersonEntity deletePerson(
            @PathVariable
            @Min(value = 1, message = "{person.id.size.error}") int id) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: deletePerson", a.getName(), "PersonControllers");
        return personService.deletePerson(id);
    }

    /**
     * Retrieves the document members associated with the person of the specified user ID.
     *
     * @param userId the user ID of the person.
     * @return a list of DocMember objects representing the document members.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/{userId}/doc_member")
    public List<DocMember> getDocTrade(
            @PathVariable
            int userId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getDocTrade", a.getName(), "PersonControllers");
        return personService.getDocTradeUnion(userId);
    }

    /**
     * Retrieves the document payments associated with the person of the specified user ID.
     *
     * @param userId the user ID of the person.
     * @return a list of DocPaymentModel objects representing the document payments.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/{userId}/doc_payment")
    public List<DocPaymentModel> getDocPayments(
            @PathVariable
            int userId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getDocPayments", a.getName(), "PersonControllers");
        return personService.getDocPayment(userId).stream().map(DocPaymentModel::toModel).collect(Collectors.toList());
    }

    /**
     * Retrieves the education information of the person with the specified user ID.
     *
     * @param userId the user ID of the person.
     * @return a string representing the education information.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/{userId}/class_education")
    public String getEducation(
            @PathVariable
            int userId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getEducation", a.getName(), "PersonControllers");
        return personService.getEducation(userId);
    }

    /**
     * Retrieves the work places associated with the person of the specified user ID.
     *
     * @param userId the user ID of the person.
     * @return a list of WorkPlace objects representing the work places.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/{userId}/workplace")
    public List<WorkPlace> getWorkPlace(
            @PathVariable
            int userId) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getWorkPlace", a.getName(), "PersonControllers");
        return personService.getWorkPlace(userId);
    }

    /**
     * Updates the person with the specified ID.
     *
     * @param id   the ID of the person to be updated.
     * @param item the updated PersonEntity object.
     * @return a PersonEntity object representing the updated person.
     */
    @PreAuthorize("hasAuthority('persons:read')")
    @PutMapping("/{id}")
    public PersonEntity update(
            @PathVariable
            int id,
            @RequestBody
            PersonEntity item) {

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: updatePerson", a.getName(), "PersonControllers");
        return personService.update(id, item);

    }

}
