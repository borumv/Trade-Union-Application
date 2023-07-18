package backend.services;

import backend.ValidationLayer.PersonValidateControler;
import backend.controllers.BaseRestController;
import backend.exceptions.PersonNotFoundException;
import backend.persist.entity.DocMember;
import backend.persist.entity.DocPayment;
import backend.persist.entity.PersonEntity;
import backend.persist.entity.WorkPlace;
import backend.persist.repositories.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for Person-related operations
 *
 * @author Boris Vlasevsky
 */

@Service
@Validated
@RequiredArgsConstructor
public class PersonService extends BaseRestController {

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private PersonValidateControler personValidateControler;

    @Autowired
    private final DocMemberService docMemberService;

    private final DocPaymentService docPaymentService;

    Logger logger = LoggerFactory.getLogger(PersonService.class);

    /**
     * Retrieves a paginated list of PersonEntity objects.
     *
     * @param pageable the pageable object specifying the page number and size
     * @return a Page containing the PersonEntity objects
     */
    public Page<PersonEntity> getPagePersons(Pageable pageable) {

        return personRepo.findAll(pageable);
    }

    /**
     * Retrieves a list of all PersonEntity objects.
     *
     * @param pageable the pageable object specifying the page number and size
     * @return a list of all PersonEntity objects
     */
    public List<PersonEntity> getAllPersons(Pageable pageable) {

        List<PersonEntity> list = new ArrayList<>();
        personRepo.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())).forEach(list::add);
        logger.info("Class: {} Action: findDocMembersByLeaveDateIsNull", "DocMemberController");
        return list;
    }

    /**
     * Retrieves a list of all PersonEntity objects.
     *
     * @return a list of all PersonEntity objects
     */
    public List<PersonEntity> getAllPersons() {

        List<PersonEntity> list = personRepo.findAll();
        logger.info("Class: {} Action: findDocMembersByLeaveDateIsNull", "DocMemberController");
        return list;
    }

    /**
     * Retrieves a list of PersonEntity objects whose first name starts with the specified pattern.
     *
     * @param patternOrder the pattern to match against the first names
     * @return a list of PersonEntity objects
     */
    public List<PersonEntity> getAllPersonsWhereNameStartWith(String patternOrder) {

        return personRepo.findByFirstNameStartsWith(patternOrder);
    }

    /**
     * Retrieves a PersonEntity object by the specified ID.
     *
     * @param id the ID of the PersonEntity object to retrieve
     * @return the retrieved PersonEntity object
     * @throws PersonNotFoundException if no PersonEntity object is found with the specified ID
     */
    public PersonEntity getPersonById(int id) throws PersonNotFoundException {

        return personRepo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException((long) id));
    }

    /**
     * Creates a new PersonEntity object.
     *
     * @param personEntity the PersonEntity object to create
     */
    public void createPerson(PersonEntity personEntity) {

        personRepo.save(personEntity);
    }

    /**
     * Deletes a PersonEntity object with the specified ID, along with associated DocMember and DocPayment objects.
     *
     * @param id the ID of the PersonEntity object to delete
     * @return the deleted PersonEntity object
     */
    @Transactional
    public PersonEntity deletePerson(int id) {

        PersonEntity personEntity = new PersonEntity();
        docPaymentService.deleteByPersonId(id);
        docMemberService.deleteByPersonId(id);
        personRepo.deleteById(id);
        return personEntity;
    }

    /**
     * Retrieves a list of DocMember objects associated with the specified person ID.
     *
     * @param id the person ID
     * @return a list of DocMember objects
     * @throws PersonNotFoundException if no PersonEntity object is found with the specified ID
     */
    public List<DocMember> getDocTradeUnion(int id) throws PersonNotFoundException {

        PersonEntity person = personRepo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException((long) id));
        return person.getDocMembers();
    }

    /**
     * Retrieves a list of DocPayment objects associated with the specified person ID.
     *
     * @param id the person ID
     * @return a list of DocPayment objects
     * @throws PersonNotFoundException if no PersonEntity object is found with the specified ID
     */
    public List<DocPayment> getDocPayment(int id) throws PersonNotFoundException {

        PersonEntity person = personRepo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException((long) id));
        return person.getDocPayments();
    }

    /**
     * Retrieves the education of a person with the specified person ID.
     *
     * @param personId the person ID
     * @return the education of the person
     * @throws PersonNotFoundException if no PersonEntity object is found with the specified ID
     */
    public String getEducation(int personId) throws PersonNotFoundException {

        PersonEntity person = personRepo.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException((long) personId));
        return person.getEducation();
    }

    /**
     * Retrieves a list of WorkPlace objects associated with the specified person ID.
     *
     * @param personId the person ID
     * @return a list of WorkPlace objects
     * @throws PersonNotFoundException if no PersonEntity object is found with the specified ID
     */
    public List<WorkPlace> getWorkPlace(int personId) throws PersonNotFoundException {

        PersonEntity person = personRepo.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException((long) personId));
        return person.getWorkPlaces();
    }

    /**
     * Updates the details of a person with the specified person ID.
     *
     * @param id           the person ID
     * @param personEntity the updated PersonEntity object
     * @return the updated PersonEntity object
     */
    public PersonEntity update(int id, PersonEntity personEntity) {

        PersonEntity personEntity1 = getPersonById(id);
        merge(personEntity1, personEntity);
        personEntity1.setId(id);
        return personValidateControler.update(personEntity1);
    }
}
