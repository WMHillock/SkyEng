package test.task.skyeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.task.skyeng.entity.MailItemEntity;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.service.MailItemService;

import java.util.List;

@RestController
@RequestMapping("/api/mail-items")
public class MailItemController {

    @Autowired
    private MailItemService mailItemService;

    @PostMapping("/register")
    public ResponseEntity<String> registerMailItem(@RequestBody MailItemEntity mailItem, @RequestParam String postalOfficeIndex) {
        PostalOfficeEntity postalOffice = mailItemService.getOffice(postalOfficeIndex);
        mailItemService.registerMailItem(mailItem, postalOffice);
        return ResponseEntity.status(HttpStatus.CREATED).body("Mail item registered successfully");
    }

    @GetMapping("/{itemId}/history")
    public ResponseEntity<List<String>> getMailItemHistory(@PathVariable Long itemId) {
        List<String> history = mailItemService.getMailItemHistory(itemId);
        return ResponseEntity.ok(history);
    }
}
