package backend.services;

import backend.ValidationLayer.PersonValidateControler;
import backend.controllers.BaseRestController;
import backend.exceptions.PersonNotFoundException;
import backend.persist.entity.*;
import backend.persist.repositories.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

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

    public Page<PersonEntity> getPagePersons(Pageable pageable) {

        return personRepo.findAll(pageable);
    }

    public List<PersonEntity> getAllPersons(Pageable pageable) {

        List<PersonEntity> list = new ArrayList<>();
        personRepo.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())).forEach(list::add);
        logger.info("Class: {} Action: findDocMembersByLeaveDateIsNull", "DocMemberController");
        return list;
    }

    public List<PersonEntity> getAllPersons() {

        List<PersonEntity> list = personRepo.findAll();
        logger.info("Class: {} Action: findDocMembersByLeaveDateIsNull", "DocMemberController");
        return list;
    }

    public List<PersonEntity> getAllPersonsWhereNameStartWith(String patternOrder) {

        return personRepo.findByFirstNameStartsWith(patternOrder);

    }

    public PersonEntity getPersonById(int id) {

        return personRepo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException((long) id));
    }

    public void createPerson(PersonEntity personEntity) {

        personRepo.save(personEntity);
    }

    @Transactional
    public PersonEntity deletePerson(int id) {

        PersonEntity personEntity = new PersonEntity();
        docPaymentService.deleteByPersonId(id);
        docMemberService.deleteByPersonId(id);
        personRepo.deleteById(id);
        return personEntity;
    }

    public List<DocMember> getDocTradeUnion(int id) {

        PersonEntity person = personRepo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException((long) id));
        return person.getDocMembers();
    }

    public List<DocPayment> getDocPayment(int id) {

        PersonEntity person = personRepo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException((long) id));
        return person.getDocPayments();
//        return docPaymentRepo.findDocPaymentByPersonId(id);
    }

    public String getEducation(int personId) {

        PersonEntity person = personRepo.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException((long) personId));
        return person.getEducation();
    }

    public List<WorkPlace> getWorkPlace(int personId) {

        PersonEntity person = personRepo.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException((long) personId));
        return person.getWorkPlaces();
    }

    public PersonEntity update(int id, PersonEntity personEntity) {

        PersonEntity personEntity1 = getPersonById(id);
        merge(personEntity1, personEntity);
        personEntity1.setId(id);
        return personValidateControler.update(personEntity1);
    }
}
