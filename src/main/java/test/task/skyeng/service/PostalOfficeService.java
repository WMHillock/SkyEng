package test.task.skyeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.repository.PostalOfficeRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
public class PostalOfficeService {

    @Autowired
    private PostalOfficeRepository postalOfficeRepository;

    public PostalOfficeEntity createPostalOffice(PostalOfficeEntity postalOffice) {
        return postalOfficeRepository.save(postalOffice);
    }

    public PostalOfficeEntity getPostalOfficeById(Long id) {
        return postalOfficeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Postal office with id " + id + " not found"));
    }

    public PostalOfficeEntity getPostalOfficeByIndex(String index) {
        return postalOfficeRepository.findByIndex(index);
    }
}

