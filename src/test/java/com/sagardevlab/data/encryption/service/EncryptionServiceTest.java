package com.sagardevlab.data.encryption.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EncryptionServiceTest {

    private final EncryptionService service = new EncryptionService();

    @Test
    void encryptLowercase() {
        assertEquals("fgh", service.encrypt("abc"));
    }

    @Test
    void encryptUppercase() {
        assertEquals("FGH", service.encrypt("ABC"));
    }

    @Test
    void encryptPreservesNonLetters() {
        assertEquals("f g!1", service.encrypt("a b!1"));
    }

    @Test
    void decryptReversesEncrypt() {
        String original = "Hello, World! 123";
        String enc = service.encrypt(original);
        assertEquals(original, service.decrypt(enc));
    }

    @Test
    void wrapAroundZ() {
        assertEquals("e", service.encrypt("z"));
        assertEquals("E", service.encrypt("Z"));
    }
}
