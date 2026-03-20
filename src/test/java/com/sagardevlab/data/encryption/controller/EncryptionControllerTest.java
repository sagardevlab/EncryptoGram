package com.sagardevlab.data.encryption.controller;

import com.sagardevlab.data.encryption.model.EncryptionRequest;
import com.sagardevlab.data.encryption.model.EncryptionResponse;
import com.sagardevlab.data.encryption.service.EncryptionService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionControllerTest {

    static class StubEncryptionService extends EncryptionService {
        @Override
        public String encrypt(String data) {
            return "enc(" + data + ")";
        }

        @Override
        public String decrypt(String data) {
            return "dec(" + data + ")";
        }
    }

    private final EncryptionService stubService = new StubEncryptionService();
    private final EncryptionController controller = new EncryptionController(stubService);

    @Test
    void encryptDataReturnsExpectedResponse() {
        EncryptionRequest req = new EncryptionRequest();
        req.setText("abc");
        EncryptionResponse resp = controller.encryptData(req);
        assertEquals("abc", resp.getOriginalText());
        assertEquals("enc(abc)", resp.getTransformedText());
        assertEquals("encrypt", resp.getOperation());
    }

    @Test
    void decryptDataReturnsExpectedResponse() {
        EncryptionRequest req = new EncryptionRequest();
        req.setText("fgh");
        EncryptionResponse resp = controller.decryptData(req);
        assertEquals("fgh", resp.getOriginalText());
        assertEquals("dec(fgh)", resp.getTransformedText());
        assertEquals("decrypt", resp.getOperation());
    }
}
