package org.projet_iwa.auth.api;

import org.junit.jupiter.api.Test;
import org.projet_iwa.auth.api.model.*;
import org.projet_iwa.auth.api.repository.UserRepository;
import org.projet_iwa.auth.api.repository.VerificationTokenRepository;
import org.projet_iwa.auth.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static  org.assertj.core.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Test
    public void createUserTest(){
        assertThat(userService).isNotNull();
        UserDTO userDTO = SampleData.getSampleDTO();
        UserResponse response = userService.createUser(userDTO);
        if(response.getType() == UserResponseType.USER_EXIST){
            User user = userRepository.findByUsername(userDTO.getUsername()).orElse(null);
            verificationTokenRepository.deleteAll();
            assert user != null;
            userRepository.deleteById(user.getUser_id());
            response = userService.createUser(userDTO);
        }
        assertThat(UserResponseType.USER_CREATED).isEqualTo(response.getType());
    }

}
