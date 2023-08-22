package test.task.skyeng.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Создание отправления, удалите поле id из Request body")
    @PostMapping
    public ResponseEntity<MailItemEntity> createMailItem(@RequestBody MailItemEntity mailItem,
                                                         @RequestParam Long currentOfficeId) {
        MailItemEntity createdMailItem = mailItemService.createMailItem(mailItem, currentOfficeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMailItem);
    }

    @Operation(summary = "Пересылка отправления в другой офис")
    @PostMapping("/{mailItemId}/send")
    public ResponseEntity<MailItemEntity> sendMailItem(@PathVariable Long mailItemId,
                                                       @RequestParam Long newOfficeId) {
        MailItemEntity sentMailItem = mailItemService.sendMailItem(mailItemId, newOfficeId);
        return ResponseEntity.ok(sentMailItem);
    }

    @Operation(summary = "Прием отправленного отправления в конечном офисе")
    @PostMapping("/{mailItemId}/receive")
    public ResponseEntity<MailItemEntity> receiveMailItem(@PathVariable Long mailItemId,
                                                          @RequestParam Long receivingOfficeId) {
        MailItemEntity receivedMailItem = mailItemService.receiveMailItem(mailItemId, receivingOfficeId);
        return ResponseEntity.ok(receivedMailItem);
    }

    @Operation(summary = "Вручение отправления конечному адресату")
    @PostMapping("/{mailItemId}/deliver")
    public ResponseEntity<MailItemEntity> deliverMailItem(@PathVariable Long mailItemId,
                                                          @RequestParam Long deliveryOffice) {
        MailItemEntity deliveredMailItem = mailItemService.deliverMailItem(mailItemId, deliveryOffice);
        return ResponseEntity.ok(deliveredMailItem);
    }

    @Operation(summary = "Вывести историю движения отправления")
    @GetMapping("/{mailItemId}/history")
    public ResponseEntity<List<MailItemHistoryEntity>> getMailItemHistory(@PathVariable Long mailItemId) {
        List<MailItemHistoryEntity> history = mailItemService.getMailItemHistory(mailItemId);
        return ResponseEntity.ok(history);
    }
}
