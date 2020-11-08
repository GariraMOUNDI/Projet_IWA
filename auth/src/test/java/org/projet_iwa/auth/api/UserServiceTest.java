package org.projet_iwa.auth.api;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.projet_iwa.auth.api.model.UserDTO;
import org.projet_iwa.auth.api.model.UserResponse;
import org.projet_iwa.auth.api.model.UserResponseType;
import org.projet_iwa.auth.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = { Main.class })
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUserTest(){
        UserDTO userDTO = getSampleDTO();
        UserResponse response = userService.createUser(userDTO);
        Assert.assertEquals(UserResponseType.USER_CREATED, response.getType());
        UserDTO new_user = response.getPayload();
        Assert.assertNotNull(new_user);
        Assert.assertEquals(userDTO.getUsername(), new_user.getUsername());
        Assert.assertEquals(userDTO.getLast_name(), new_user.getLast_name());
        Assert.assertEquals(userDTO.getFirst_name(), new_user.getFirst_name());
        Assert.assertEquals(userDTO.getEmail(), new_user.getEmail());
        Assert.assertNotEquals(new_user.getUser_id(), null);
    }

    private UserDTO getSampleDTO(){
        UserDTO dto = new UserDTO(
                null,
                "parti",
                "parti",
                "parti",
                "parti",
                "parti@parti.com",
                "1548697"
        );
        dto.setConfirm_pass("parti");
        return dto;
    }
}
