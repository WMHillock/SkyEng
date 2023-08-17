package test.task.skyeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.entity.enums.InteractionType;
import test.task.skyeng.repository.MailItemHistoryRepository;

import java.time.LocalDateTime;

@Service
public class MailItemHistoryService {

    private final MailItemHistoryRepository historyRepository;

    @Autowired
    public MailItemHistoryService(MailItemHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void saveHistory(MailItemEntity mailItem, InteractionType state, PostalOfficeEntity currentOffice) {
        MailItemHistoryEntity historyEntry = MailItemHistoryEntity.builder()
                .mailItemId(mailItem.getId())
                .interactionType(state)
                .timestamp(LocalDateTime.now())
                .currentOffice(currentOffice)
                .build();

        historyRepository.save(historyEntry);
    }
}
