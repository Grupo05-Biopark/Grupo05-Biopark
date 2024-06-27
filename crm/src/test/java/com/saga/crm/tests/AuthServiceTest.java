package com.saga.crm.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        // Endpoint para o login
        String endpoint = "/login";

        // Mock da requisição de login com dados fictícios
        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                        .param("username", "usuario_teste@teste.com")
                        .param("password", "senha_teste"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Login bem-sucedido"));
    }
}