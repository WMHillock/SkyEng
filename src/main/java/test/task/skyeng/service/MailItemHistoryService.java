package test.task.skyeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.entity.enums.InteractionType;
import test.task.skyeng.repository.MailItemHistoryRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import test.task.skyeng.repository.PostalOfficeRepository;

@Service
@Transactional
public class MailItemHistoryService {

    private final MailItemHistoryRepository historyRepository;
    private final PostalOfficeRepository postalRepository;

    @Autowired
    public MailItemHistoryService(MailItemHistoryRepository historyRepository, PostalOfficeRepository postalRepository) {
        this.historyRepository = historyRepository;
        this.postalRepository = postalRepository;
    }

    public void saveHistory(MailItemEntity mailItem, InteractionType state, PostalOfficeEntity currentOffice) {

        MailItemHistoryEntity historyEntry = MailItemHistoryEntity.builder()
                .mailItem(mailItem)
                .interactionType(state)
                .timestamp(LocalDateTime.now())
                .currentOffice(currentOffice.getId())
                .officeToDelivery(mailItem.getRecipientIndex())
                .build();

        historyRepository.save(historyEntry);

    }
}
