package test.task.skyeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.task.skyeng.entity.InteractionType;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.repository.MailItemHistoryRepository;
import test.task.skyeng.repository.MailItemRepository;
import test.task.skyeng.repository.PostalOfficeRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MailItemService {

    @Autowired
    private MailItemRepository mailItemRepository;

    @Autowired
    private PostalOfficeRepository postalOfficeRepository;

    @Autowired
    private MailItemHistoryRepository mailItemHistoryRepository;

    public void registerMailItem(MailItemEntity mailItem, PostalOfficeEntity postalOffice) {
        if (mailItem == null) {
            throw new IllegalArgumentException("Mail item cannot be null");
        }

        MailItemEntity newMailItem = createMailItem(mailItem);
        Optional<PostalOfficeEntity> destinationOffice = getDestinationOffice(mailItem);
        MailItemHistoryEntity itemHistory = createMailItemHistory(mailItem, postalOffice, destinationOffice.orElse(null));

        saveMailItem(newMailItem);
        saveMailItemHistory(itemHistory);
    }


    public List<String> getMailItemHistory(Long itemId) {
        Optional<MailItemEntity> optionalMailItem = mailItemRepository.findById(itemId);
        if (optionalMailItem.isPresent()) {
            MailItemEntity mailItem = optionalMailItem.get();
            return mailItemHistoryRepository.findByMailItemOrderByInteractionDate(mailItem)
                    .stream()
                    .map(this::generateInteractionDescription)
                    .collect(Collectors.toList());
        }
        return List.of();
    }


    public void copyMailItemProperties(MailItemEntity source, MailItemEntity destination) {
        destination.setType(source.getType());
        destination.setRecipientIndex(source.getRecipientIndex());
        destination.setRecipientAddress(source.getRecipientAddress());
        destination.setRecipientName(source.getRecipientName());
    }

    public String generateInteractionDescription(MailItemHistoryEntity interaction) {
        if (interaction != null) {
            StringBuilder descriptionBuilder = new StringBuilder();
            descriptionBuilder.append("Date: ").append(interaction.getInteractionDate())
                    .append(", Source: ").append(interaction.getSourceOffice().getName())
                    .append(", Destination: ").append(interaction.getDestinationOffice().getName())
                    .append(", Status: ").append(interaction.getInteractionType());

            return descriptionBuilder.toString();
        }
        return "";
    }


    public PostalOfficeEntity getOffice(String officeIndex) {
        return postalOfficeRepository.findByIndex(officeIndex);
    }

    public MailItemEntity createMailItem(MailItemEntity mailItem) {
        MailItemEntity newMailItem = new MailItemEntity();
        copyMailItemProperties(mailItem, newMailItem);
        return newMailItem;
    }

    public Optional<PostalOfficeEntity> getDestinationOffice(MailItemEntity mailItem) {
        return Optional.ofNullable(getOffice(mailItem.getRecipientIndex()));
    }

    public MailItemHistoryEntity createMailItemHistory(MailItemEntity mailItem, PostalOfficeEntity sourceOffice, PostalOfficeEntity destinationOffice) {
        MailItemHistoryEntity itemHistory = new MailItemHistoryEntity();
        itemHistory.setMailItem(mailItem);
        itemHistory.setSourceOffice(sourceOffice);
        itemHistory.setDestinationOffice(destinationOffice);
        itemHistory.setInteractionDate(new Date());
        itemHistory.setInteractionType(InteractionType.REGISTERED);
        return itemHistory;
    }

    public void saveMailItem(MailItemEntity mailItem) {
        mailItemRepository.save(mailItem);
    }

    public void saveMailItemHistory(MailItemHistoryEntity itemHistory) {
        mailItemHistoryRepository.save(itemHistory);
    }
}
