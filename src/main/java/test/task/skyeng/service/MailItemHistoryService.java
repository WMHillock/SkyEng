package test.task.skyeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.entity.enums.InteractionType;
import test.task.skyeng.repository.MailItemHistoryRepository;
import test.task.skyeng.repository.PostalOfficeRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class MailItemHistoryService {

    private final MailItemHistoryRepository historyRepository;
    private final PostalOfficeRepository postalOfficeRepository;

    @Autowired
    public MailItemHistoryService(MailItemHistoryRepository historyRepository, PostalOfficeRepository postalOfficeRepository) {
        this.historyRepository = historyRepository;
        this.postalOfficeRepository = postalOfficeRepository;
    }

    public MailItemHistoryEntity saveHistory(MailItemEntity mailItem, InteractionType interactionType, Long currentOfficeId) {
        PostalOfficeEntity deliveryOffice = getOfficeByIndex(mailItem);
        PostalOfficeEntity currentOffice = getOfficeById(currentOfficeId);
        MailItemHistoryEntity historyEntry = createHistoryEntry(mailItem, interactionType, currentOffice, deliveryOffice);

        return historyRepository.save(historyEntry);
    }

    public PostalOfficeEntity getOfficeByIndex(MailItemEntity mailItem) {
        if (mailItem == null) {
            throw new IllegalArgumentException("mailItem cannot be null");
        }
        return postalOfficeRepository.findByIndex(mailItem.getRecipientIndex());
    }

    public PostalOfficeEntity getOfficeById(Long currentOfficeId) {
        return postalOfficeRepository.findById(currentOfficeId)
                .orElseThrow(() -> new EntityNotFoundException("Office not found"));
    }

    private MailItemHistoryEntity createHistoryEntry(MailItemEntity mailItem, InteractionType interactionType,
                                                     PostalOfficeEntity currentOffice, PostalOfficeEntity deliveryOffice) {
        return MailItemHistoryEntity.builder()
                .mailItem(mailItem)
                .interactionType(interactionType)
                .timestamp(LocalDateTime.now())
                .onTheWay(interactionType == InteractionType.SENT)
                .currentOffice(currentOffice)
                .officeToDelivery(deliveryOffice)
                .build();
    }
}
