package backend.controllers;

import backend.persist.entity.*;
import backend.persist.models.*;
import backend.services.PersonService;
import io.swagger.annotations.ApiImplicitParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/all{page}")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    public List<AbstractModel> showPersonsList(@PathVariable int page) {
//        List<PersonEntity> list = personService.getAllPersons();
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

    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping()
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
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

    @PreAuthorize("hasAuthority('persons:read')")
    @GetMapping("/allOrder")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    public List<PersonModel> showPersonsListWhereNameStartWith(@PathVariable String startWith) {
        List<PersonEntity> list = personService.getAllPersonsWhereNameStartWith(startWith);
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: showPersonsListWhereNameStartWith", a.getName(), "PersonControllers");
        return list.stream().map(PersonModel::toModel).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('persons:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @GetMapping("/{userId}")
    public PersonEntity getById(@PathVariable @Min(value = 1, message = "{person.id.size.error}") int userId) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getById", a.getName(), "PersonControllers");
        return personService.getPersonById(userId);
    }

    @PreAuthorize("hasAuthority('persons:write')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @PostMapping()
    public PersonModel addPerson(@RequestBody @Valid PersonEntity personEntity) {
        logger.trace("addPerson method accesed");
        personEntity.setUpdate(Timestamp.valueOf(LocalDateTime.now()));
        personService.createPerson(personEntity);
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: addPerson", a.getName(), "PersonControllers");
        return PersonModel.toModel(personEntity);
    }

    @PreAuthorize("hasAuthority('persons:delete')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @DeleteMapping("/{id}")
    public PersonEntity deletePerson(@PathVariable @Min(value = 1, message = "{person.id.size.error}") int id) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: deletePerson", a.getName(), "PersonControllers");
        return personService.deletePerson(id);
    }

    @PreAuthorize("hasAuthority('persons:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @GetMapping("/{userId}/doc_member")
    public List<DocMember> getDocTrade(int userId) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getDocTrade", a.getName(), "PersonControllers");
        return personService.getDocTradeUnion(userId);
    }

    @PreAuthorize("hasAuthority('persons:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @GetMapping("/{userId}/doc_payment")
    public List<DocPaymentModel> getDocPayments(@PathVariable int userId) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getDocPayments", a.getName(), "PersonControllers");
        return personService.getDocPayment(userId).stream().map(DocPaymentModel::toModel).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('persons:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @GetMapping("/{userId}/class_education")
    public String getEducation(int userId) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getEducation", a.getName(), "PersonControllers");
        return personService.getEducation(userId);
    }

    @PreAuthorize("hasAuthority('persons:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @GetMapping("/{userId}/workplace")
    public List<WorkPlace> getWorkPlace(int personId) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: getWorkPlace", a.getName(), "PersonControllers");
        return personService.getWorkPlace(personId);
    }

    @PreAuthorize("hasAuthority('persons:read')")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "XXX")
    @PutMapping("/{id}")
    public PersonEntity update(@PathVariable int id, @RequestBody PersonEntity item) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        logger.info("UserId: {}. Class: {} Action: updatePerson", a.getName(), "PersonControllers");
        return personService.update(id, item);

    }
}
