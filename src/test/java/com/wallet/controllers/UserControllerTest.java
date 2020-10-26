package com.wallet.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.user.dto.UserDTO;
import com.wallet.user.entities.User;
import com.wallet.user.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// Teste a requisição no endpoit do usuário

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest() // Só carrega o UserController
@AutoConfigureMockMvc
public class UserControllerTest {

    private static final String EMAIL = "email@test.com";
    private static final String NAME = "User Test";
    private static final String PASSWORD = "123456";
    private static final String URL = "/user";

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("it should be able to save a new user")
    public void saveUser() throws Exception {

        BDDMockito.given(userService.save(Mockito.any(User.class))).willReturn(getMockUser());

        mvc.perform(
                MockMvcRequestBuilders.post(URL)
                .content(getJsonPayload())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    public User getMockUser(){
        User user = new User();
        user.setEmail(EMAIL);
        user.setName(NAME);
        user.setPassword(PASSWORD);

        return user;
    }

    public String getJsonPayload() throws JsonProcessingException {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(EMAIL);
        userDTO.setName(NAME);
        userDTO.setPassword(PASSWORD);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(userDTO);
    }
}
