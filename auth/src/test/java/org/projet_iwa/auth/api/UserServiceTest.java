package org.projet_iwa.auth.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.projet_iwa.auth.api.config.KeycloakService;
import org.projet_iwa.auth.api.model.*;
import org.projet_iwa.auth.api.repository.UserRepository;
import org.projet_iwa.auth.api.repository.VerificationTokenRepository;
import org.projet_iwa.auth.api.service.MailUtil;
import org.projet_iwa.auth.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static  org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@AutoConfigureMockMvc
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFactory userFactory;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private VerificationTokenRepository verificationTokenRepository;

    @MockBean
    private KeycloakService keycloakService;

    @MockBean
    private MailUtil mailUtil;

    @Test
    @DisplayName("Create user without error")
    public void createUser(){
        doReturn(false)
                .when(userRepository).existsByUsername(any());
        doReturn(false)
                .when(userRepository).existsByEmail(any());
        doReturn(null)
                .when(userRepository).saveAndFlush(any());
        doReturn(null)
                .when(verificationTokenRepository).saveAndFlush(any());

        UserResponse response = userService.createUser(SampleData.getSampleDTO());

        assertThat(UserResponseType.USER_CREATED).isEqualTo(response.getType());
    }

    @Test
    @DisplayName("Login user without error")
    public void loginUser() {
        doReturn(Optional.of(SampleData.getSampleUser()))
                .when(userRepository).findByUsername(any());
        doReturn(SampleData.getSampleDTO().getToken())
                .when(keycloakService).getToken(any(), any());

        UserResponse response = userService.loginUser(SampleData.getSampleDTO().getUsername(), SampleData.getSampleDTO().getPassword());

        assertThat(response.getType()).isEqualTo(UserResponseType.USER_LOGGED_IN);
        assertThat(response.getPayload().getToken().getToken()).isEqualTo(SampleData.getSampleDTO().getToken().getToken());
    }

}
