package test.task.skyeng.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.repository.PostalOfficeRepository;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostalOfficeServiceTest {

    @Mock
    private PostalOfficeRepository postalOfficeRepository;

    private PostalOfficeService postalOfficeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        postalOfficeService = new PostalOfficeService(postalOfficeRepository);
    }

    @Test
    public void testCreatePostalOffice() {
        PostalOfficeEntity postalOffice = new PostalOfficeEntity();

        when(postalOfficeRepository.save(any(PostalOfficeEntity.class))).thenReturn(postalOffice);

        PostalOfficeEntity createdPostalOffice = postalOfficeService.createPostalOffice(postalOffice);

        assertNotNull(createdPostalOffice);
        assertEquals(postalOffice, createdPostalOffice);

        verify(postalOfficeRepository, times(1)).save(postalOffice);
    }

    @Test
    public void testGetPostalOfficeById() {
        Long officeId = 1L;
        PostalOfficeEntity postalOfficeEntity = new PostalOfficeEntity();

        when(postalOfficeRepository.findById(officeId)).thenReturn(java.util.Optional.of(postalOfficeEntity));

        PostalOfficeEntity retrievedPostalOffice = postalOfficeService.getPostalOfficeById(officeId);

        assertNotNull(retrievedPostalOffice);
        assertEquals(postalOfficeEntity, retrievedPostalOffice);
    }

    @Test
    public void testGetPostalOfficeByIdNotFound() {
        Long officeId = 1L;

        when(postalOfficeRepository.findById(officeId)).thenReturn(java.util.Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> postalOfficeService.getPostalOfficeById(officeId));
    }

    @Test
    public void testGetPostalOfficeByIndex() {
        String index = "123";
        PostalOfficeEntity postalOfficeEntity = new PostalOfficeEntity();

        when(postalOfficeRepository.findByIndex(index)).thenReturn(postalOfficeEntity);

        PostalOfficeEntity retrievedPostalOffice = postalOfficeService.getPostalOfficeByIndex(index);

        assertNotNull(retrievedPostalOffice);
        assertEquals(postalOfficeEntity, retrievedPostalOffice);
    }

    @Test
    public void testGetPostalOfficeByIndexNotFound() {
        String index = "123";

        when(postalOfficeRepository.findByIndex(index)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> postalOfficeService.getPostalOfficeByIndex(index));
    }
}
