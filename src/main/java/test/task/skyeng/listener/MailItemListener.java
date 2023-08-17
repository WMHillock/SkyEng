package test.task.skyeng.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.enums.InteractionType;
import test.task.skyeng.service.MailItemHistoryService;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Component
public class MailItemListener {

    @Autowired
    @Lazy
    private MailItemHistoryService historyService;

    @PrePersist
    @PreUpdate
    public void prePersistOrUpdate(MailItemEntity mailItem) {
        InteractionType currentState = mailItem.getState();
        historyService.saveHistory(mailItem, currentState, mailItem.getCurrentOffice());
    }
}



