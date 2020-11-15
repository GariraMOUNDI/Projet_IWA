package org.projet_iwa.auth.api;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.projet_iwa.auth.api.model.*;
import org.projet_iwa.auth.api.repository.UserRepository;
import org.projet_iwa.auth.api.repository.VerificationTokenRepository;
import org.projet_iwa.auth.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = { Main.class })
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

//    @Test
//    public void createUserTest(){
//        UserDTO userDTO = getSampleDTO();
//        UserResponse response = userService.createUser(userDTO);
//        if(response.getType() == UserResponseType.USER_EXIST){
//            User user = userRepository.findByUsername(userDTO.getUsername()).orElse(null);
//            verificationTokenRepository.deleteAll();
//            assert user != null;
//            userRepository.deleteById(user.getUser_id());
//            response = userService.createUser(userDTO);
//        }
//        Assert.assertEquals(UserResponseType.USER_CREATED, response.getType());
//    }

    private UserDTO getSampleDTO(){
        UserDTO dto = new UserDTO(
                null,
                "parti",
                "parti",
                "parti",
                "parti",
                "parti@parti.com",
                "1548697",
                false
        );
        dto.setConfirm_pass("parti");
        return dto;
    }
}
