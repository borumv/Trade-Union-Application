package backend.services;
import backend.exceptions.DocPaymentNotFoundException;
import backend.persist.entity.DocPayment;
import backend.persist.models.QuantityPayTradeUnion;
import backend.persist.repositories.DocPaymentRepo;
import backend.persist.repositories.QuantityPayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class DocPaymentServiceTest {

    @Mock
    private DocPaymentRepo paymentRepo;

    @Mock
    private QuantityPayment qantityPaymentRepo;

    @InjectMocks
    private DocPaymentService docPaymentService;

    @Test
    void getAllReturnsListOfDocPayments() {
        // Arrange
        List<DocPayment> expectedDocPayments = Arrays.asList(new DocPayment(), new DocPayment());
        when(paymentRepo.findAll()).thenReturn(expectedDocPayments);
        // Act
        List<DocPayment> result = docPaymentService.getAll();
        // Assert
        verify(paymentRepo, times(1)).findAll();
        assertEquals(expectedDocPayments, result);
    }

    @Test
    void getPayCountOfTradeUnionReturnsListOfQuantityPayTradeUnion() {
        // Arrange
        List<QuantityPayTradeUnion> expectedPayTradeUnions = Arrays.asList(new QuantityPayTradeUnion(), new QuantityPayTradeUnion());
        when(qantityPaymentRepo.getQuantityPay()).thenReturn(expectedPayTradeUnions);
        // Act
        List<QuantityPayTradeUnion> result = docPaymentService.getPayCountOfTradeUnion();
        // Assert
        verify(qantityPaymentRepo, times(1)).getQuantityPay();
        assertEquals(expectedPayTradeUnions, result);
    }

    @Test
    void getDontPaiedReturnsListOfQuantityPayTradeUnion() {
        // Arrange
        List<QuantityPayTradeUnion> expectedDontPaidTradeUnions = Arrays.asList(new QuantityPayTradeUnion(), new QuantityPayTradeUnion());
        when(qantityPaymentRepo.getDontPaied()).thenReturn(expectedDontPaidTradeUnions);
        // Act
        List<QuantityPayTradeUnion> result = docPaymentService.getDontPaied();
        // Assert
        verify(qantityPaymentRepo, times(1)).getDontPaied();
        assertEquals(expectedDontPaidTradeUnions, result);
    }

    @Test
    void findByIdExistingIdReturnsDocPayment() {
        // Arrange
        int id = 1;
        DocPayment expectedDocPayment = new DocPayment();
        when(paymentRepo.findById(id)).thenReturn(Optional.of(expectedDocPayment));
        // Act
        DocPayment result = docPaymentService.findById(id);
        // Assert
        verify(paymentRepo, times(1)).findById(id);
        assertEquals(expectedDocPayment, result);
    }

    @Test
    void findByIdNonExistingIdThrowsDocPaymentNotFoundException() {
        // Arrange
        int id = 1;
        when(paymentRepo.findById(id)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(DocPaymentNotFoundException.class, () -> docPaymentService.findById(id));
        verify(paymentRepo, times(1)).findById(id);
    }

    @Test
    void createDocPaymentReturnsCreatedDocPayment() {
        // Arrange
        DocPayment docPayment = new DocPayment();
        when(paymentRepo.save(docPayment)).thenReturn(docPayment);
        // Act
        DocPayment result = docPaymentService.createDocPayment(docPayment);
        // Assert
        verify(paymentRepo, times(1)).save(docPayment);
        assertEquals(docPayment, result);
    }

    @Test
    void updateExistingPaymentIdReturnsUpdatedDocPayment() {
        // Arrange
        int paymentId = 1;
        DocPayment expectedDocPayment = new DocPayment();
        expectedDocPayment.setEndPay(new Date());
        expectedDocPayment.setId(paymentId);
        when(paymentRepo.findById(paymentId)).thenReturn(Optional.of(expectedDocPayment));
        when(paymentRepo.save(expectedDocPayment)).thenReturn(expectedDocPayment);
        // Act
        DocPayment result = docPaymentService.update(paymentId);
        // Assert
        verify(paymentRepo, times(1)).findById(paymentId);
        verify(paymentRepo, times(1)).save(expectedDocPayment);
        assertEquals(expectedDocPayment, result);
    }

    @Test
    void updateNonExistingPaymentIdThrowsDocPaymentNotFoundException() {
        // Arrange
        int paymentId = 1;
        when(paymentRepo.findById(paymentId)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(DocPaymentNotFoundException.class, () -> docPaymentService.update(paymentId));
        verify(paymentRepo, times(1)).findById(paymentId);
        verify(paymentRepo, never()).save(any(DocPayment.class));
    }

    @Test
    void deletePaymentReturnsDeletedDocPayment() {
        // Arrange
        int id = 1;
        DocPayment expectedDeletedPayment = new DocPayment();
        doNothing().when(paymentRepo).deleteById(id);
        // Act
        DocPayment result = docPaymentService.deletePayment(id);
        // Assert
        verify(paymentRepo, times(1)).deleteById(id);
        assertEquals(expectedDeletedPayment, result);
    }
}
