package test.task.skyeng.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.service.PostalOfficeService;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostalOfficeControllerTest {

    @Mock
    private PostalOfficeService postalOfficeService;

    private PostalOfficeController postalOfficeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        postalOfficeController = new PostalOfficeController(postalOfficeService);
    }

    @Test
    public void testCreatePostalOffice() {
        PostalOfficeEntity postalOffice = new PostalOfficeEntity();

        when(postalOfficeService.createPostalOffice(any(PostalOfficeEntity.class))).thenReturn(postalOffice);

        ResponseEntity<PostalOfficeEntity> response = postalOfficeController.createPostalOffice(postalOffice);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(postalOffice, response.getBody());
    }

    @Test
    public void testGetPostalOfficeById() {
        Long officeId = 1L;
        PostalOfficeEntity postalOfficeEntity = new PostalOfficeEntity();

        when(postalOfficeService.getPostalOfficeById(officeId)).thenReturn(postalOfficeEntity);

        ResponseEntity<PostalOfficeEntity> response = postalOfficeController.getPostalOfficeById(officeId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postalOfficeEntity, response.getBody());
    }

    @Test
    public void testGetPostalOfficeByIndex() {
        String index = "123";
        PostalOfficeEntity postalOfficeEntity = new PostalOfficeEntity();

        when(postalOfficeService.getPostalOfficeByIndex(index)).thenReturn(postalOfficeEntity);

        ResponseEntity<PostalOfficeEntity> response = postalOfficeController.getPostalOfficeByIndex(index);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postalOfficeEntity, response.getBody());
    }

    @Test
    public void testGetPostalOfficeByIndexNotFound() {
        String index = "123";

        when(postalOfficeService.getPostalOfficeByIndex(index)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> postalOfficeController.getPostalOfficeByIndex(index));
    }
}
