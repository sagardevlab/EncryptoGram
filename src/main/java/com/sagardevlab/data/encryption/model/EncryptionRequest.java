package com.sagardevlab.data.encryption.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EncryptionRequest {
    @NotBlank(message = "text must not be blank")
    private String text;
}
