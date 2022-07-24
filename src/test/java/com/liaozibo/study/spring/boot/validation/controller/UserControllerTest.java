package com.liaozibo.study.spring.boot.validation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liaozibo.study.spring.boot.validation.dao.UserRepository;
import com.liaozibo.study.spring.boot.validation.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
class UserControllerTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldBeOK() throws Exception {
        String user = objectMapper.writeValueAsString(
                User.builder()
                        .name("Jerry")
                        .email("jerry@qq.com")
                        .build()
        );
        mockMvc.perform(
                post("/user")
                    .content(user)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldBeFail() throws Exception {
        String user = objectMapper.writeValueAsString(
                User.builder()
                        .email("jerry@qq.com")
                        .build()
        );
        mockMvc.perform(
                post("/user")
                    .content(user)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", is("Name is mandatory")));
    }
}