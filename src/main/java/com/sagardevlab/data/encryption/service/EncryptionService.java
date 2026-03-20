package com.sagardevlab.data.encryption.service;

import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    
    private static final int SHIFT = 5; 

    public String encrypt(String data) {
        return process(data, SHIFT);
    }

    public String decrypt(String data) {
        return process(data, 26-(SHIFT%26));
    }

    public String process(String text, int shift){
        StringBuilder result = new StringBuilder();
        for(char character : text.toCharArray()){
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                char transformed = (char) ((character - base + shift) % 26 + base);             
                result.append(transformed);
            } else {
                result.append(character); 
            }
        }
        return result.toString();
    }
}
