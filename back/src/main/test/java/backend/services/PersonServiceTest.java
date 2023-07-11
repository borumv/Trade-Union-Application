package backend.services;
import backend.ValidationLayer.PersonValidateControler;
import backend.exceptions.PersonNotFoundException;
import backend.persist.entity.DocMember;
import backend.persist.entity.DocPayment;
import backend.persist.entity.PersonEntity;
import backend.persist.entity.WorkPlace;
import backend.persist.repositories.PersonRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class PersonServiceTest {

    @Mock
    private PersonRepo personRepo;

    @Mock
    private PersonValidateControler personValidateControler;

    @InjectMocks
    private PersonService personService;

    @Test
    public void testGetPagePersons() {

        Page<PersonEntity> expectedPage = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        when(personRepo.findAll(pageable)).thenReturn(expectedPage);
        Page<PersonEntity> actualPage = personService.getPagePersons(pageable);
        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void testGetAllPersons() {

        List<PersonEntity> expectedPersons = new ArrayList<>();
        when(personRepo.findAll()).thenReturn(expectedPersons);
        List<PersonEntity> actualPersons = personService.getAllPersons();
        assertEquals(expectedPersons, actualPersons);
        verify(personRepo, times(1)).findAll();
    }

    @Test
    public void testGetAllPersonsWhereNameStartWith() {

        String patternOrder = "abc";
        List<PersonEntity> expectedPersons = new ArrayList<>();
        when(personRepo.findByFirstNameStartsWith(patternOrder, PageRequest.of(1, 3, Sort.by("fn"))))
                .thenReturn(expectedPersons);
        List<PersonEntity> actualPersons = personService.getAllPersonsWhereNameStartWith(patternOrder);
        assertEquals(expectedPersons, actualPersons);
        verify(personRepo, times(1)).findByFirstNameStartsWith(patternOrder, PageRequest.of(1, 3, Sort.by("fn")));
    }

    @Test
    public void testGetPersonById_ExistingId_ReturnsPersonEntity() {

        int id = 1;
        PersonEntity expectedPerson = new PersonEntity();
        when(personRepo.findById(id)).thenReturn(Optional.of(expectedPerson));
        PersonEntity actualPerson = personService.getPersonById(id);
        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void testGetPersonById_NonExistingId_ThrowsPersonNotFoundException() {

        int id = 1;
        when(personRepo.findById(id)).thenReturn(Optional.empty());
        assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(id));
    }

    @Test
    public void testCreatePerson() {

        PersonEntity personEntity = new PersonEntity();
        personService.createPerson(personEntity);
        verify(personRepo, times(1)).save(personEntity);
    }

    @Test
    public void testDeletePerson() {

        int id = 1;
        PersonEntity personEntity = new PersonEntity();
        doNothing().when(personRepo).deleteById(id);
        PersonEntity deletedPerson = personService.deletePerson(id);
        assertEquals(personEntity, deletedPerson);
        verify(personRepo, times(1)).deleteById(id);
    }

    @Test
    public void testGetDocTradeUnion() {

        int id = 1;
        PersonEntity personEntity = new PersonEntity();
        List<DocMember> expectedDocMembers = new ArrayList<>();
        personEntity.setDocMembers(expectedDocMembers);
        when(personRepo.findById(id)).thenReturn(Optional.of(personEntity));
        List<DocMember> actualDocMembers = personService.getDocTradeUnion(id);
        assertEquals(expectedDocMembers, actualDocMembers);
    }

    @Test
    public void testGetDocPayment() {

        int id = 1;
        PersonEntity personEntity = new PersonEntity();
        List<DocPayment> expectedDocPayments = new ArrayList<>();
        personEntity.setDocPayments(expectedDocPayments);
        when(personRepo.findById(id)).thenReturn(Optional.of(personEntity));
        List<DocPayment> actualDocPayments = personService.getDocPayment(id);
        assertEquals(expectedDocPayments, actualDocPayments);
    }

    @Test
    public void testGetEducation() {

        int personId = 1;
        PersonEntity personEntity = new PersonEntity();
        String expectedEducation = "Education";
        personEntity.setEducation(expectedEducation);
        when(personRepo.findById(personId)).thenReturn(Optional.of(personEntity));
        String actualEducation = personService.getEducation(personId);
        assertEquals(expectedEducation, actualEducation);
    }

    @Test
    public void testGetWorkPlace() {

        int personId = 1;
        PersonEntity personEntity = new PersonEntity();
        List<WorkPlace> expectedWorkPlaces = new ArrayList<>();
        personEntity.setWorkPlaces(expectedWorkPlaces);
        when(personRepo.findById(personId)).thenReturn(Optional.of(personEntity));
        List<WorkPlace> actualWorkPlaces = personService.getWorkPlace(personId);
        assertEquals(expectedWorkPlaces, actualWorkPlaces);
    }

    @Test
    public void testUpdate() {

        int id = 1;
        PersonEntity existingPersonEntity = new PersonEntity();
        existingPersonEntity.setId(id);
        PersonEntity updatedPersonEntity = new PersonEntity();
        when(personRepo.findById(id)).thenReturn(Optional.of(existingPersonEntity));
        when(personValidateControler.update(existingPersonEntity)).thenReturn(updatedPersonEntity);
        PersonEntity actualUpdatedPerson = personService.update(id, updatedPersonEntity);
        assertEquals(updatedPersonEntity, actualUpdatedPerson);
        verify(personValidateControler, times(1)).update(existingPersonEntity);
    }
}