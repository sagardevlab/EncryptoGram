package com.sagardevlab.data.encryption.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EncryptionResponse {
    String originalText;
    String transformedText;
    String operation;
}
