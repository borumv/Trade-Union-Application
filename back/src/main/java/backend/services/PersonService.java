package backend.services;


import backend.ValidationLayer.PersonValidateControler;
import backend.controllers.BaseRestController;
import backend.exceptions.PersonNotFoundException;
import backend.persist.entity.*;
import backend.persist.repositories.*;
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
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class PersonService  extends BaseRestController {

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private PersonValidateControler personValidateControler;

    Logger logger = LoggerFactory.getLogger(PersonService.class);

    public Page<PersonEntity> getPagePersons(Pageable pageable) {
                return personRepo.findAll(pageable);
    }


    public List<PersonEntity> getAllPersons(Pageable pageable) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        List<PersonEntity> list = new ArrayList<>();
        personRepo.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize())).forEach(list::add);
        logger.info("UserId: {}. Class: {} Action: findDocMembersByLeaveDateIsNull",  a.getName(), "DocMemberController");
        return list;
    }

    public List<PersonEntity> getAllPersons() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        List<PersonEntity> list = personRepo.findAll();

        logger.info("UserId: {}. Class: {} Action: findDocMembersByLeaveDateIsNull",  a.getName(), "DocMemberController");
        return list;
    }

    public List<PersonEntity> getAllPersonsWhereNameStartWith(String patternOrder) {
        return personRepo.findByFirstNameStartsWith(patternOrder, PageRequest.of(1, 3, Sort.by("fn")));

    }

    public PersonEntity getPersonById(int id) {
        return personRepo.findById(id)
                .orElseThrow(() -> new PersonNotFoundException((long) id));
    }

    public void createPerson(PersonEntity personEntity) {
        personRepo.save(personEntity);
    }

    public PersonEntity deletePerson(int id) {
        PersonEntity personEntity = new PersonEntity();
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

    public PersonEntity update(int id, PersonEntity personEntity){
        PersonEntity personEntity1 = getPersonById(id);
        merge(personEntity1, personEntity);
        personEntity1.setId(id);
//        personEntity1.setFirstName(personEntity.getFirstName());
//        personEntity1.setLastName(personEntity.getLastName());
//        personEntity1.setBirth(personEntity.getBirth());
//        personEntity1.setEducation(personEntity.getEducation());
//        personEntity1.setUpdate(Timestamp.valueOf(LocalDateTime.now()));

        return  personValidateControler.update(personEntity1);
    }
}
