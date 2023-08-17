package test.task.skyeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.service.MailItemService;

import java.util.List;

@RestController
@RequestMapping("/api/mail-items")
public class MailItemController {

    private final MailItemService mailItemService;

    @Autowired
    public MailItemController(MailItemService mailItemService) {
        this.mailItemService = mailItemService;
    }

    @PostMapping
    public ResponseEntity<MailItemEntity> createMailItem(@RequestBody MailItemEntity mailItem) {
        MailItemEntity createdMailItem = mailItemService.createMailItem(mailItem);
        return new ResponseEntity<>(createdMailItem, HttpStatus.CREATED);
    }

    @PostMapping("/{mailItemId}/send")
    public ResponseEntity<MailItemEntity> sendMailItem(
            @PathVariable Long mailItemId,
            @RequestBody PostalOfficeEntity newOffice) {
        MailItemEntity sentMailItem = mailItemService.sendMailItem(mailItemId, newOffice);
        return new ResponseEntity<>(sentMailItem, HttpStatus.OK);
    }

    @PostMapping("/{mailItemId}/receive")
    public ResponseEntity<MailItemEntity> receiveMailItem(
            @PathVariable Long mailItemId,
            @RequestBody PostalOfficeEntity receivingOffice) {
        MailItemEntity receivedMailItem = mailItemService.receiveMailItem(mailItemId, receivingOffice);
        return new ResponseEntity<>(receivedMailItem, HttpStatus.OK);
    }

    @PostMapping("/{mailItemId}/deliver")
    public ResponseEntity<MailItemEntity> deliverMailItem(@PathVariable Long mailItemId) {
        MailItemEntity deliveredMailItem = mailItemService.deliverMailItem(mailItemId);
        return new ResponseEntity<>(deliveredMailItem, HttpStatus.OK);
    }

    @GetMapping("/{mailItemId}/history")
    public ResponseEntity<List<MailItemHistoryEntity>> getMailItemHistory(@PathVariable Long mailItemId) {
        List<MailItemHistoryEntity> history = mailItemService.getMailItemHistory(mailItemId);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}
