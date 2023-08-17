package test.task.skyeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.task.skyeng.entity.PostalOfficeEntity;
import test.task.skyeng.service.PostalOfficeService;

@RestController
@RequestMapping("/api/postal-offices")
public class PostalOfficeController {

    @Autowired
    private PostalOfficeService postalOfficeService;

    @PostMapping
    public ResponseEntity<PostalOfficeEntity> createPostalOffice(@RequestBody PostalOfficeEntity postalOffice) {
        PostalOfficeEntity createdOffice = postalOfficeService.createPostalOffice(postalOffice);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOffice);
    }
}
