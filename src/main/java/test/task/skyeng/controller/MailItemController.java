package test.task.skyeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.MailItemHistoryEntity;
import test.task.skyeng.service.MailItemService;

import java.util.List;

@RestController
@RequestMapping("/mail-items")
public class MailItemController {

    private final MailItemService mailItemService;

    @Autowired
    public MailItemController(MailItemService mailItemService) {
        this.mailItemService = mailItemService;
    }

    @PostMapping
    public ResponseEntity<MailItemEntity> createMailItem(@RequestBody MailItemEntity mailItem,
                                                         @RequestParam Long currentOfficeId) {
        MailItemEntity createdMailItem = mailItemService.createMailItem(mailItem, currentOfficeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMailItem);
    }

    @PostMapping("/{mailItemId}/send")
    public ResponseEntity<MailItemEntity> sendMailItem(@PathVariable Long mailItemId,
                                                       @RequestParam Long newOfficeId) {
        MailItemEntity sentMailItem = mailItemService.sendMailItem(mailItemId, newOfficeId);
        return ResponseEntity.ok(sentMailItem);
    }

    @PostMapping("/{mailItemId}/receive")
    public ResponseEntity<MailItemEntity> receiveMailItem(@PathVariable Long mailItemId,
                                                          @RequestParam Long receivingOfficeId) {
        MailItemEntity receivedMailItem = mailItemService.receiveMailItem(mailItemId, receivingOfficeId);
        return ResponseEntity.ok(receivedMailItem);
    }

    @PostMapping("/{mailItemId}/deliver")
    public ResponseEntity<MailItemEntity> deliverMailItem(@PathVariable Long mailItemId) {
        MailItemEntity deliveredMailItem = mailItemService.deliverMailItem(mailItemId);
        return ResponseEntity.ok(deliveredMailItem);
    }

    @GetMapping("/{mailItemId}/history")
    public ResponseEntity<List<MailItemHistoryEntity>> getMailItemHistory(@PathVariable Long mailItemId) {
        List<MailItemHistoryEntity> history = mailItemService.getMailItemHistory(mailItemId);
        return ResponseEntity.ok(history);
    }
}
