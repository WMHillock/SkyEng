package test.task.skyeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.entity.enums.InteractionType;
import test.task.skyeng.repository.MailItemHistoryRepository;
import test.task.skyeng.repository.MailItemRepository;

import java.time.LocalDateTime;

@Service
public class MailItemHistoryService {

    private final MailItemHistoryRepository historyRepository;
    private final MailItemRepository mailItemRepository;

    @Autowired
    public MailItemHistoryService(MailItemHistoryRepository historyRepository, MailItemRepository mailItemRepository) {
        this.historyRepository = historyRepository;
        this.mailItemRepository = mailItemRepository;
    }

    public void saveHistory(MailItemEntity mailItem, InteractionType state, PostalOfficeEntity currentOffice) {

        MailItemHistoryEntity historyEntry = MailItemHistoryEntity.builder()
                .mailItem(mailItem)
                .interactionType(state)
                .timestamp(LocalDateTime.now())
                .currentOffice(currentOffice)
                .build();

        historyRepository.save(historyEntry);
    }
}
