package backend.services;
import backend.exceptions.DocMemberNotFoundException;
import backend.persist.entity.DocMember;
import backend.persist.repositories.DocTradeUnionRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class DocMemberServiceTest {

    @Mock
    private DocTradeUnionRepo docRepo;

    @InjectMocks
    private DocMemberService docMemberService;

    @Test
    void getAllWhoNotFinishedReturnsListOfDocMembers() {
        // Arrange
        List<DocMember> expectedDocMembers = Arrays.asList(new DocMember(), new DocMember());
        when(docRepo.findDocMembersByLeaveDateIsNull()).thenReturn(expectedDocMembers);
        // Act
        List<DocMember> result = docMemberService.getAllWhoNotFinished();
        // Assert
        verify(docRepo, times(1)).findDocMembersByLeaveDateIsNull();
        assertEquals(expectedDocMembers, result);
    }

    @Test
    void findByNumExistingNumReturnsDocMember() {
        // Arrange
        int num = 123;
        DocMember expectedDocMember = new DocMember();
        when(docRepo.findDocMemberByMembershipCard(num)).thenReturn(Optional.of(expectedDocMember));
        // Act
        DocMember result = docMemberService.findByNum(num);
        // Assert
        verify(docRepo, times(1)).findDocMemberByMembershipCard(num);
        assertEquals(expectedDocMember, result);
    }

    @Test
    void findByNumNonExistingNumThrowsDocMemberNotFoundException() {
        // Arrange
        int num = 123;
        when(docRepo.findDocMemberByMembershipCard(num)).thenReturn(Optional.empty());
        // Act & Assert
        assertThrows(DocMemberNotFoundException.class, () -> docMemberService.findByNum(num));
        verify(docRepo, times(1)).findDocMemberByMembershipCard(num);
    }
}