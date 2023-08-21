package test.task.skyeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.entity.enums.InteractionType;
import test.task.skyeng.repository.MailItemHistoryRepository;
import test.task.skyeng.repository.MailItemRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MailItemService {

    private final MailItemRepository mailItemRepository;
    private final MailItemHistoryRepository historyRepository;

    @Autowired
    public MailItemService(MailItemRepository mailItemRepository, MailItemHistoryRepository historyRepository) {
        this.mailItemRepository = mailItemRepository;
        this.historyRepository = historyRepository;
    }

    public MailItemEntity createMailItem(MailItemEntity mailItem) {
        mailItem.setState(InteractionType.CREATED);
        return mailItemRepository.save(mailItem);
    }

    public MailItemEntity sendMailItem(Long mailItemId, PostalOfficeEntity newOffice) {
        MailItemEntity mailItem = mailItemRepository.findById(mailItemId)
                .orElseThrow(() -> new EntityNotFoundException("Mail item not found"));

        mailItem.setCurrentOffice(newOffice);
        mailItem.setState(InteractionType.SENT);
        return mailItemRepository.save(mailItem);
    }

    public MailItemEntity receiveMailItem(Long mailItemId, PostalOfficeEntity receivingOffice) {
        MailItemEntity mailItem = mailItemRepository.findById(mailItemId)
                .orElseThrow(() -> new EntityNotFoundException("Mail item not found"));

        mailItem.setCurrentOffice(receivingOffice);
        mailItem.setState(InteractionType.ARRIVED);
        return mailItemRepository.save(mailItem);
    }

    public MailItemEntity deliverMailItem(Long mailItemId) {
        MailItemEntity mailItem = mailItemRepository.findById(mailItemId)
                .orElseThrow(() -> new EntityNotFoundException("Mail item not found"));

        mailItem.setState(InteractionType.DELIVERED);
        return mailItemRepository.save(mailItem);
    }

    public List<MailItemHistoryEntity> getMailItemHistory(Long mailItemId) {
        return historyRepository.findByMailItemIdOrderByTimestampDesc(mailItemId);
    }
}
