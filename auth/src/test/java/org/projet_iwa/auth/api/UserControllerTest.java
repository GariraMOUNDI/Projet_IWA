package org.projet_iwa.auth.api;

import org.junit.jupiter.api.Test;
import org.projet_iwa.auth.api.config.KeycloakInit;
import org.projet_iwa.auth.api.model.Response;
import org.projet_iwa.auth.api.model.UserResponse;
import org.projet_iwa.auth.api.model.UserResponseType;
import org.projet_iwa.auth.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService iUserService;

    @Autowired
    private KeycloakInit keycloakInit;

    @Test
    public void createUser() throws Exception {
        doReturn(new UserResponse(UserResponseType.USER_CREATED))
                .when(iUserService).createUser(any());

        mockMvc.perform(
                post("/users/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(SampleData.toJson(SampleData.getSampleDTO()))
        ).andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.type").value(UserResponseType.USER_CREATED.toString()))
        .andExpect(jsonPath("$.payload").isEmpty());
    }

    private String token;

    @Test
    public void loginUser() throws Exception {
        doReturn(new UserResponse(UserResponseType.USER_LOGGED_IN, SampleData.getSampleDTO()))
                .when(iUserService).loginUser(any(),any());

        mockMvc.perform(
                post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(SampleData.toJson(SampleData.getSampleDTO()))
        ).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.type").value(UserResponseType.USER_LOGGED_IN.toString()))
        .andExpect(jsonPath("$.payload").isNotEmpty())
        .andExpect(jsonPath("$.payload.token.access_token").value(SampleData.getSampleDTO().getToken().getToken()));
    }

    @Test
    public void confirmUser() throws Exception {
        doReturn(new UserResponse(UserResponseType.USER_CONFIRMED))
                .when(iUserService).confirmUser(any());

        mockMvc.perform(
                get("/users/confirmUser?token=qsjdlkqshkdhqsdlkqsjlksqflkhq")
            ).andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.type").value(UserResponseType.USER_CONFIRMED.toString()))
            .andExpect(jsonPath("$.payload").isEmpty());
    }

    @Test
    public void forgotUser() throws Exception {
        doReturn(new UserResponse(UserResponseType.FORGOT_EMAIL))
                .when(iUserService).forgotPassword(any());

        mockMvc.perform(
                post("/users/forgotUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(SampleData.toJson(SampleData.getSampleDTO()))
        ).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.type").value(UserResponseType.FORGOT_EMAIL.toString()))
        .andExpect(jsonPath("$.payload").isEmpty());
    }

    // Enlever la sécurité (@RolesAllowed) dans le controlleur avant de tester
    @Test
    public void changeStatus() throws Exception {
        doReturn(new UserResponse(UserResponseType.STATUS_CHANGE))
                .when(iUserService).changeStatus(any(), any());

        mockMvc.perform(
                get("/users/changeStatus/15?status=CONTACT")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type").value(UserResponseType.STATUS_CHANGE.toString()))
                .andExpect(jsonPath("$.payload").isEmpty());
    }

    // Enlever la sécurité (@RolesAllowed) dans le controlleur avant de tester
    @Test
    public void getEmail() throws Exception {
        doReturn(new Response<>(UserResponseType.EMAIL_SEND, SampleData.getSampleDTO().getEmail()))
                .when(iUserService).getUserEmail(any());

        mockMvc.perform(
                get("/users/email/15")
        ).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type").value(UserResponseType.EMAIL_SEND.toString()))
                .andExpect(jsonPath("$.payload").value(SampleData.getSampleDTO().getEmail()));
    }
}
