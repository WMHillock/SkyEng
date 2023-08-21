package test.task.skyeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.entity.enums.InteractionType;
import test.task.skyeng.repository.MailItemHistoryRepository;
import test.task.skyeng.repository.MailItemRepository;
import test.task.skyeng.repository.PostalOfficeRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MailItemService {

    private final MailItemRepository mailItemRepository;
    private final MailItemHistoryRepository historyRepository;
    private final MailItemHistoryService mailItemHistoryService;
    private final PostalOfficeRepository postalOfficeRepository;

    @Autowired
    public MailItemService(MailItemRepository mailItemRepository, MailItemHistoryRepository historyRepository, MailItemHistoryService mailItemHistoryService, PostalOfficeRepository postalOfficeRepository) {
        this.mailItemRepository = mailItemRepository;
        this.historyRepository = historyRepository;
        this.mailItemHistoryService = mailItemHistoryService;
        this.postalOfficeRepository = postalOfficeRepository;
    }

    public MailItemEntity createMailItem(MailItemEntity mailItem, Long currentOfficeId) {
        mailItemHistoryService.saveHistory(mailItem, InteractionType.CREATED, currentOfficeId);
        return mailItemRepository.save(mailItem);
    }

    public MailItemEntity sendMailItem(Long mailItemId, Long newOfficeId) {
        MailItemEntity mailItem = getMailItemById(mailItemId);

        if (newOfficeId == null) {
            throw new IllegalArgumentException("New office ID cannot be null");
        }

        mailItemHistoryService.saveHistory(mailItem, InteractionType.SENT, newOfficeId);
        return mailItemRepository.save(mailItem);
    }

    public MailItemEntity receiveMailItem(Long mailItemId, Long receivingOfficeId) {
        MailItemEntity mailItem = getMailItemById(mailItemId);

        if (receivingOfficeId == null) {
            throw new IllegalArgumentException("Receiving office ID cannot be null");
        }

        mailItemHistoryService.saveHistory(mailItem, InteractionType.ARRIVED, receivingOfficeId);
        return mailItemRepository.save(mailItem);
    }

    public MailItemEntity deliverMailItem(Long mailItemId) {
        MailItemEntity mailItem = getMailItemById(mailItemId);
        Long currentOfficeId = getOfficeIdByIndex(mailItem.getRecipientIndex());

        if (currentOfficeId == null) {
            throw new EntityNotFoundException("Postal office not found for index: " + mailItem.getRecipientIndex());
        }

        mailItemHistoryService.saveHistory(mailItem, InteractionType.DELIVERED, currentOfficeId);
        return mailItemRepository.save(mailItem);
    }

    public List<MailItemHistoryEntity> getMailItemHistory(Long mailItemId) {
        return historyRepository.findByMailItemIdOrderByTimestampDesc(mailItemId);
    }

    private MailItemEntity getMailItemById(Long mailItemId) {
        return mailItemRepository.findById(mailItemId)
                .orElseThrow(() -> new EntityNotFoundException("Mail item not found"));
    }

    private Long getOfficeIdByIndex(String index) {
        PostalOfficeEntity office = postalOfficeRepository.findByIndex(index);
        if (office == null) {
            throw new EntityNotFoundException("Postal office not found for index: " + index);
        }
        return office.getId();
    }
}

