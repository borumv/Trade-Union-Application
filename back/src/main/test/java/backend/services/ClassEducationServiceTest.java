package backend.services;
import backend.exceptions.ClassEducationNotFoundException;
import backend.persist.entity.ClassEducation;
import backend.persist.entity.PersonEntity;
import backend.persist.repositories.ClassEducationRepo;
import backend.persist.repositories.PersonRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class ClassEducationServiceTest {

    @Mock
    private ClassEducationRepo classEducationRepo;

    @Mock
    private PersonRepo personRepo;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ClassEducationService classEducationService;

    @Test
    void getAllWhoTakeThisTypeEducationValidTypeEducationIdReturnsListOfPersons() {
        // Arrange
        int typeEducationId = 1;
        String nameEducation = "Education Name";
        ClassEducation classEducation = new ClassEducation();
        classEducation.setName(nameEducation);
        List<PersonEntity> expectedPersons = Arrays.asList(new PersonEntity(), new PersonEntity());
        when(classEducationRepo.findById(typeEducationId)).thenReturn(Optional.of(classEducation));
        Query query = mock(Query.class);
        when(entityManager.createNativeQuery(anyString(), anyString())).thenReturn(query);
        when(query.setParameter(ArgumentMatchers.any(Integer.class), eq(nameEducation))).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedPersons);
        // Act
        List<PersonEntity> result = classEducationService.getAllWhoTakeThisTypeEducation(typeEducationId);
        // Assert
        verify(classEducationRepo, times(1)).findById(typeEducationId);
        verify(entityManager, times(1)).createNativeQuery(anyString(), anyString());
        verify(query, times(1)).setParameter(ArgumentMatchers.any(Integer.class), eq(nameEducation));
        verify(query, times(1)).getResultList();
        assertEquals(expectedPersons, result);
    }

    @Test
    void getAllType() {

        when(classEducationRepo.findAll()).thenReturn(Arrays.asList(new ClassEducation(), new ClassEducation()));
        assertEquals(2, classEducationService.getAllType().size());
        verify(classEducationRepo, times(1)).findAll();
    }

    @Test
    public void findById() {

        int id = 1;
        ClassEducation expectedClassEducation = new ClassEducation();
        when(classEducationRepo.findById(id)).thenReturn(Optional.of(expectedClassEducation));
        // Act
        ClassEducation result = classEducationService.findById(id);
        // Assert
        verify(classEducationRepo, times(1)).findById(id);
        assertEquals(expectedClassEducation, result);
    }

    @Test
    void findById_NonExistingId_ThrowsClassEducationNotFoundException() {
        // Arrange
        int id = 1;
        when(classEducationRepo.findById(id)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(ClassEducationNotFoundException.class, () -> classEducationService.findById(id));
        verify(classEducationRepo, times(1)).findById(id);
    }
}