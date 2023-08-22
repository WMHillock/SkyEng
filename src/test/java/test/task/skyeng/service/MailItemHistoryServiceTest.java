package test.task.skyeng.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.entity.enums.InteractionType;
import test.task.skyeng.entity.enums.ItemType;
import test.task.skyeng.repository.MailItemHistoryRepository;
import test.task.skyeng.repository.PostalOfficeRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MailItemHistoryServiceTest {

    @Mock
    private MailItemHistoryRepository historyRepository;

    @Mock
    private PostalOfficeRepository postalOfficeRepository;

    private MailItemHistoryService mailItemHistoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mailItemHistoryService = new MailItemHistoryService(historyRepository, postalOfficeRepository);
    }

    @Test
    public void testSaveHistory() {
        MailItemEntity mailItem = new MailItemEntity();
        mailItem.setId(1L);
        mailItem.setType(ItemType.LETTER);
        mailItem.setRecipientIndex("123");
        mailItem.setRecipientAddress("Some Address");
        mailItem.setRecipientName("John Doe");

        InteractionType interactionType = InteractionType.SENT;
        Long currentOfficeId = 1L;
        PostalOfficeEntity deliveryOffice = new PostalOfficeEntity();
        PostalOfficeEntity currentOffice = new PostalOfficeEntity();

        when(postalOfficeRepository.findByIndex(anyString())).thenReturn(deliveryOffice);
        when(postalOfficeRepository.findById(currentOfficeId)).thenReturn(java.util.Optional.of(currentOffice));

        MailItemHistoryEntity savedHistoryEntry = new MailItemHistoryEntity();
        savedHistoryEntry.setMailItem(mailItem); // Set the mailItem in history entry
        savedHistoryEntry.setInteractionType(interactionType); // Set the interactionType
        savedHistoryEntry.setCurrentOffice(currentOffice); // Set the currentOffice
        savedHistoryEntry.setOfficeToDelivery(deliveryOffice); // Set the officeToDelivery
        savedHistoryEntry.setTimestamp(LocalDateTime.now()); // Set timestamp
        savedHistoryEntry.setOnTheWay(true); // Set onTheWay to true

        when(historyRepository.save(any(MailItemHistoryEntity.class))).thenReturn(savedHistoryEntry);

        MailItemHistoryEntity historyEntry = mailItemHistoryService.saveHistory(mailItem, interactionType, currentOfficeId);

        assertNotNull(historyEntry);
        assertEquals(mailItem, historyEntry.getMailItem());
        assertEquals(interactionType, historyEntry.getInteractionType());
        assertTrue(historyEntry.isOnTheWay());
        assertEquals(currentOffice, historyEntry.getCurrentOffice());
        assertEquals(deliveryOffice, historyEntry.getOfficeToDelivery());
    }





    // Write similar test cases for other methods

    @Test
    public void testGetOfficeByIndex() {
        MailItemEntity mailItem = new MailItemEntity();
        mailItem.setRecipientIndex("123");
        PostalOfficeEntity deliveryOffice = new PostalOfficeEntity();

        when(postalOfficeRepository.findByIndex(anyString())).thenReturn(deliveryOffice);

        PostalOfficeEntity retrievedOffice = mailItemHistoryService.getOfficeByIndex(mailItem);

        assertNotNull(retrievedOffice);
        assertEquals(deliveryOffice, retrievedOffice);
    }

    @Test
    public void testGetOfficeById() {
        Long currentOfficeId = 1L;
        PostalOfficeEntity currentOffice = new PostalOfficeEntity();

        when(postalOfficeRepository.findById(currentOfficeId)).thenReturn(java.util.Optional.of(currentOffice));

        PostalOfficeEntity retrievedOffice = mailItemHistoryService.getOfficeById(currentOfficeId);

        assertNotNull(retrievedOffice);
        assertEquals(currentOffice, retrievedOffice);
    }

    @Test
    public void testGetOfficeByIdNotFound() {
        Long currentOfficeId = 1L;

        when(postalOfficeRepository.findById(currentOfficeId)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> mailItemHistoryService.getOfficeById(currentOfficeId));
    }
}
