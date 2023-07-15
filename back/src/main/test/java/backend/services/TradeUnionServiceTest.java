package backend.services;
import backend.exceptions.TradeUnionNotFoundExeption;
import backend.persist.entity.DocPayment;
import backend.persist.entity.PersonEntity;
import backend.persist.entity.TradeUnion;
import backend.persist.models.TradeUnionModel;
import backend.persist.repositories.DocTradeUnionRepo;
import backend.persist.repositories.PersonRepo;
import backend.persist.repositories.TradeUnionRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class TradeUnionServiceTest {

    @Mock
    private TradeUnionRepo tradeUnionRepo;

    @Mock
    private PersonRepo personRepo;

    @Mock
    private DocTradeUnionRepo docTradeUnionRepo;

    @Mock
    private EntityManager em;

    @Mock
    private Query query;

    @InjectMocks
    private TradeUnionService tradeUnionService;

    @Test
    public void testGetAllDocPayments() {
        int unionId = 1;
        List<DocPayment> expectedDocPayments = new ArrayList<>();
        when(em.createNativeQuery(anyString(), eq("DocPaymentMapping"))).thenReturn(query);
        when(query.setParameter(anyInt(), eq(unionId))).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedDocPayments);

        List<DocPayment> actualDocPayments = tradeUnionService.getAllDocPayments(unionId);

        assertEquals(expectedDocPayments, actualDocPayments);
    }


    @Test
    public void testGetAllActiveMembers() {
        int unionId = 1;
        Pageable pageable = mock(Pageable.class);
        List<PersonEntity> expectedMembers = new ArrayList<>();
        when(personRepo.getAllActiveMembers(unionId, pageable)).thenReturn(expectedMembers);

        List<PersonEntity> actualMembers = tradeUnionService.getAllActiveMembers(unionId, pageable);

        assertEquals(expectedMembers, actualMembers);
    }

    @Test
    public void testGetAllActiveWithoutPageable() {
        int unionId = 1;
        List<PersonEntity> expectedMembers = new ArrayList<>();
        when(personRepo.getAllActiveWithoutPageable(unionId)).thenReturn(expectedMembers);

        List<PersonEntity> actualMembers = tradeUnionService.getAllActiveWithoutPageable(unionId);

        assertEquals(expectedMembers, actualMembers);
    }

    @Test
    public void testGetAllTradeUnions() {
        List<TradeUnion> tradeUnionList = new ArrayList<>();
        when(tradeUnionRepo.findAll()).thenReturn(tradeUnionList);

        List<TradeUnionModel> tradeUnionModels = tradeUnionService.getAllTradeUnions();

        assertEquals(tradeUnionList.size(), tradeUnionModels.size());
    }

    @Test
    public void testGetAllNaturalTradeUnions() {
        List<TradeUnion> tradeUnionList = new ArrayList<>();
        when(tradeUnionRepo.findAll()).thenReturn(tradeUnionList);

        List<TradeUnion> actualTradeUnions = tradeUnionService.getAllNaturalTradeUnions();

        assertEquals(tradeUnionList, actualTradeUnions);
    }

//    @Test
//    public void testCreateTradeUnion() {
//        TradeUnion expectedTradeUnion = new TradeUnion();
//        when(tradeUnionRepo.save(any(TradeUnion.class))).thenReturn(expectedTradeUnion);
//
//       // TradeUnion actualTradeUnion = tradeUnionService.createTradeUnion(tradeUnionModel);
//
//        assertEquals(expectedTradeUnion, actualTradeUnion);
//    }

    @Test
    public void testDeleteTradeUnion() {
        int id = 1;
        TradeUnion expectedTradeUnion = new TradeUnion();
        doNothing().when(tradeUnionRepo).deleteById(id);

        TradeUnion actualTradeUnion = tradeUnionService.deleteTradeUnion(id);

        assertEquals(expectedTradeUnion, actualTradeUnion);
        verify(tradeUnionRepo, times(1)).deleteById(id);
    }

    @Test
    public void testGetByIdExistingIdReturnsTradeUnion() {
        int id = 1;
        TradeUnion expectedTradeUnion = new TradeUnion();
        when(tradeUnionRepo.findById(id)).thenReturn(java.util.Optional.of(expectedTradeUnion));

        TradeUnion actualTradeUnion = tradeUnionService.getById(id);

        assertEquals(expectedTradeUnion, actualTradeUnion);
    }

    @Test
    public void testGetByIdNonExistingIdThrowsTradeUnionNotFoundException() {
        int id = 1;
        when(tradeUnionRepo.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(TradeUnionNotFoundExeption.class, () -> tradeUnionService.getById(id));
    }

    @Test
    public void testUpdate() {
        int id = 1;
        TradeUnion existingTradeUnion = new TradeUnion();
        TradeUnion updatedTradeUnion = new TradeUnion();
        when(tradeUnionRepo.findById(id)).thenReturn(java.util.Optional.of(existingTradeUnion));
        when(tradeUnionRepo.save(any(TradeUnion.class))).thenReturn(updatedTradeUnion);

        TradeUnion actualUpdatedTradeUnion = tradeUnionService.update(id, updatedTradeUnion);

        assertEquals(updatedTradeUnion, actualUpdatedTradeUnion);
        verify(tradeUnionRepo, times(1)).save(any(TradeUnion.class));
    }
}