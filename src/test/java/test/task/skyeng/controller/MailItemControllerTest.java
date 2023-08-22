package test.task.skyeng.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.service.MailItemService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MailItemControllerTest {

    @Mock
    private MailItemService mailItemService;

    private MailItemController mailItemController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mailItemController = new MailItemController(mailItemService);
    }

    @Test
    public void testCreateMailItem() {
        MailItemEntity mailItem = new MailItemEntity();
        Long currentOfficeId = 1L;

        when(mailItemService.createMailItem(any(MailItemEntity.class), eq(currentOfficeId))).thenReturn(mailItem);

        ResponseEntity<MailItemEntity> response = mailItemController.createMailItem(mailItem, currentOfficeId);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mailItem, response.getBody());
    }

    @Test
    public void testSendMailItem() {
        Long mailItemId = 1L;
        Long newOfficeId = 2L;
        MailItemEntity mailItem = new MailItemEntity();

        when(mailItemService.sendMailItem(eq(mailItemId), eq(newOfficeId))).thenReturn(mailItem);

        ResponseEntity<MailItemEntity> response = mailItemController.sendMailItem(mailItemId, newOfficeId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mailItem, response.getBody());
    }

    @Test
    public void testReceiveMailItem() {
        Long mailItemId = 1L;
        Long receivingOfficeId = 2L;
        MailItemEntity mailItem = new MailItemEntity();

        when(mailItemService.receiveMailItem(eq(mailItemId), eq(receivingOfficeId))).thenReturn(mailItem);

        ResponseEntity<MailItemEntity> response = mailItemController.receiveMailItem(mailItemId, receivingOfficeId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mailItem, response.getBody());
    }

    @Test
    public void testDeliverMailItem() {
        Long mailItemId = 1L;
        MailItemEntity mailItem = new MailItemEntity();

        when(mailItemService.deliverMailItem(eq(mailItemId))).thenReturn(mailItem);

        ResponseEntity<MailItemEntity> response = mailItemController.deliverMailItem(mailItemId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mailItem, response.getBody());
    }

    @Test
    public void testGetMailItemHistory() {
        Long mailItemId = 1L;
        List<MailItemHistoryEntity> history = new ArrayList<>();

        when(mailItemService.getMailItemHistory(eq(mailItemId))).thenReturn(history);

        ResponseEntity<List<MailItemHistoryEntity>> response = mailItemController.getMailItemHistory(mailItemId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(history, response.getBody());
    }
}
