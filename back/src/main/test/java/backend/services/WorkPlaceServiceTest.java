package backend.services;
import backend.exceptions.WorkPlaceNotFoundException;
import backend.persist.entity.WorkPlace;
import backend.persist.repositories.WorkPlaceRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class WorkPlaceServiceTest {

    @Mock
    private WorkPlaceRepo workPlaceRepo;

    @InjectMocks
    private WorkPlaceService workPlaceService;

    @Test
    public void testGetAllWorkPlace() {

        List<WorkPlace> expectedWorkPlaces = new ArrayList<>();
        when(workPlaceRepo.findAll()).thenReturn(expectedWorkPlaces);
        List<WorkPlace> actualWorkPlaces = workPlaceService.getAllWorkPlace();
        assertEquals(expectedWorkPlaces, actualWorkPlaces);
    }

    @Test
    public void testGetByIdExistingIdReturnsWorkPlace() {

        int id = 1;
        WorkPlace expectedWorkPlace = new WorkPlace();
        when(workPlaceRepo.findById(id)).thenReturn(Optional.of(expectedWorkPlace));
        WorkPlace actualWorkPlace = workPlaceService.getById(id);
        assertEquals(expectedWorkPlace, actualWorkPlace);
    }

    @Test
    public void testGetByIdNonExistingIdThrowsWorkPlaceNotFoundException() {

        int id = 1;
        when(workPlaceRepo.findById(id)).thenReturn(Optional.empty());
        assertThrows(WorkPlaceNotFoundException.class, () -> workPlaceService.getById(id));
    }
}