package test.task.skyeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.task.skyeng.entity.InteractionType;
import test.task.skyeng.service.PostalOfficeService;

@RestController
@RequestMapping("/api/postal-offices")
public class PostalOfficeController {

    @Autowired
    private PostalOfficeService postalOfficeService;

    @PostMapping("/{officeId}/items/{itemId}/process")
    public ResponseEntity<String> processMailInteraction(
            @PathVariable Long officeId,
            @PathVariable Long itemId,
            @RequestParam InteractionType interactionType) {

        postalOfficeService.processMailInteraction(officeId, itemId, interactionType);
        return ResponseEntity.status(HttpStatus.OK).body("Mail interaction processed successfully");
    }
}
