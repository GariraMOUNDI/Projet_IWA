package org.projet_iwa.auth.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.projet_iwa.auth.api.model.UserDTO;
import org.projet_iwa.auth.api.model.UserResponse;
import org.projet_iwa.auth.api.model.UserResponseType;
import org.projet_iwa.auth.api.service.IMailSender;
import org.projet_iwa.auth.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService iUserService;

    @Test
    public void createUser() throws Exception {
        when(iUserService.createUser(SampleData.getSampleDTO()))
                .thenReturn(null);

        mockMvc.perform(
                post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(SampleData.toJson(SampleData.getSampleDTO())))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
