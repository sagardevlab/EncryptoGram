package com.sagardevlab.data.encryption.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import com.sagardevlab.data.encryption.service.EncryptionService;
import com.sagardevlab.data.encryption.model.EncryptionResponse;
import com.sagardevlab.data.encryption.model.EncryptionRequest;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class EncryptionController {
    
    private final EncryptionService encryptionService;

    @PostMapping("/encrypt")
    public EncryptionResponse encryptData(@Valid @RequestBody EncryptionRequest encryptionRequest) {
        String data = encryptionRequest.getText();
        String encryptedData = encryptionService.encrypt(data);
        return new EncryptionResponse(data, encryptedData, "encrypt");
    }

    @PostMapping("/decrypt")
    public EncryptionResponse decryptData(@Valid @RequestBody EncryptionRequest encryptionRequest) {
        String data = encryptionRequest.getText();
        String decryptedData = encryptionService.decrypt(data);
        return new EncryptionResponse(data, decryptedData, "decrypt");
    }

}
