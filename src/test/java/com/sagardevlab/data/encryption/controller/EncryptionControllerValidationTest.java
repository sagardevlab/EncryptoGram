package com.sagardevlab.data.encryption.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sagardevlab.data.encryption.model.EncryptionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EncryptionControllerValidationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;
    private ObjectMapper objectMapper;

    @org.junit.jupiter.api.BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void returnsValidationErrorForBlankText() throws Exception {
        EncryptionRequest req = new EncryptionRequest();
        req.setText("");

        mockMvc.perform(post("/api/encrypt")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.error").value("Validation Failed"))
            .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void returnsMalformedJsonError() throws Exception {
        String bad = "{ invalid json";

        mockMvc.perform(post("/api/encrypt")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bad))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.error").value("Malformed JSON"))
            .andExpect(jsonPath("$.message").exists());
    }
}
