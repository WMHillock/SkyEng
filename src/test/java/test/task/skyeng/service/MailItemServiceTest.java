package test.task.skyeng.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.entity.enums.InteractionType;
import test.task.skyeng.repository.MailItemHistoryRepository;
import test.task.skyeng.repository.MailItemRepository;
import test.task.skyeng.repository.PostalOfficeRepository;
import test.task.skyeng.service.MailItemHistoryService;
import test.task.skyeng.service.MailItemService;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class MailItemServiceTest {

    @Mock
    private MailItemRepository mailItemRepository;

    @Mock
    private MailItemHistoryRepository historyRepository;

    @Mock
    private MailItemHistoryService mailItemHistoryService;

    @Mock
    private PostalOfficeRepository postalOfficeRepository;

    private MailItemService mailItemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mailItemService = new MailItemService(
                mailItemRepository,
                historyRepository,
                mailItemHistoryService,
                postalOfficeRepository
        );
    }

    @Test
    public void testCreateMailItem() {
        MailItemEntity mailItem = new MailItemEntity();
        Long currentOfficeId = 1L;

        when(mailItemRepository.save(any(MailItemEntity.class))).thenReturn(mailItem);

        MailItemEntity createdMailItem = mailItemService.createMailItem(mailItem, currentOfficeId);

        assertNotNull(createdMailItem);
        assertEquals(mailItem, createdMailItem);

        verify(mailItemHistoryService, times(1)).saveHistory(mailItem, InteractionType.CREATED, currentOfficeId);
        verify(mailItemRepository, times(1)).save(mailItem);
    }

    // Write similar test cases for other methods

    @Test
    public void testGetMailItemById() {
        Long mailItemId = 1L;
        MailItemEntity mailItemEntity = new MailItemEntity();

        when(mailItemRepository.findById(mailItemId)).thenReturn(java.util.Optional.of(mailItemEntity));

        MailItemEntity retrievedMailItem = mailItemService.getMailItemById(mailItemId);

        assertNotNull(retrievedMailItem);
        assertEquals(mailItemEntity, retrievedMailItem);
    }

    @Test
    public void testGetOfficeIdByIndex() {
        String index = "123";
        PostalOfficeEntity postalOfficeEntity = new PostalOfficeEntity();
        postalOfficeEntity.setId(1L);

        when(postalOfficeRepository.findByIndex(index)).thenReturn(postalOfficeEntity);

        Long retrievedOfficeId = mailItemService.getOfficeIdByIndex(index);

        assertNotNull(retrievedOfficeId);
        assertEquals(postalOfficeEntity.getId(), retrievedOfficeId);
    }

    @Test
    public void testGetOfficeIdByIndexNotFound() {
        String index = "123";

        when(postalOfficeRepository.findByIndex(index)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> mailItemService.getOfficeIdByIndex(index));
    }
}
