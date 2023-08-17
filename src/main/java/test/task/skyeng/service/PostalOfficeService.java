package test.task.skyeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.skyeng.entity.InteractionType;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.repository.MailItemHistoryRepository;
import test.task.skyeng.repository.MailItemRepository;
import test.task.skyeng.repository.PostalOfficeRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class PostalOfficeService {

    @Autowired
    private PostalOfficeRepository postalOfficeRepository;

    @Autowired
    private MailItemRepository mailItemRepository;

    @Autowired
    private MailItemHistoryRepository mailItemHistoryRepository;

    public void processMailInteraction(Long officeId, Long itemId, InteractionType interactionType) {
        findOfficeById(officeId).ifPresent(office -> {
            findMailItemById(itemId).ifPresent(mailItem -> {
                createAndSaveInteractionHistory(mailItem, office, interactionType);
            });
        });
    }

    public Optional<PostalOfficeEntity> findOfficeById(Long officeId) {
        return postalOfficeRepository.findById(officeId);
    }

    public Optional<MailItemEntity> findMailItemById(Long itemId) {
        return mailItemRepository.findById(itemId);
    }

    public void createAndSaveInteractionHistory(MailItemEntity mailItem, PostalOfficeEntity office, InteractionType interactionType) {
        final PostalOfficeEntity destinationOffice = (interactionType == InteractionType.ARRIVED) ?
                postalOfficeRepository.findByIndex(mailItem.getRecipientIndex()) :
                office;

        MailItemHistoryEntity itemHistory = new MailItemHistoryEntity();
        itemHistory.setMailItem(mailItem);
        itemHistory.setSourceOffice(office);
        itemHistory.setDestinationOffice(destinationOffice);
        itemHistory.setInteractionDate(new Date());
        itemHistory.setInteractionType(interactionType);

        mailItemHistoryRepository.save(itemHistory);
    }
}
