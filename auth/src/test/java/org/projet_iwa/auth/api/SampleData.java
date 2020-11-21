package org.projet_iwa.auth.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.representations.AccessTokenResponse;
import org.projet_iwa.auth.api.model.User;
import org.projet_iwa.auth.api.model.UserDTO;
import org.projet_iwa.auth.api.model.UserFactory;
import org.projet_iwa.auth.api.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;

public class SampleData {

    private static final ObjectMapper map = new ObjectMapper();

    private static final UserFactory userFactory = new UserFactory();

    public static UserDTO getSampleDTO(){
        UserDTO dto = new UserDTO(
                null,
                "parti",
                "parti",
                "parti",
                "parti",
                "parti@gazert.com",
                "1548697",
                true
        );
        dto.setConfirm_pass("parti");
        AccessTokenResponse at = new AccessTokenResponse();
        at.setToken("qsflkqhslkfjqhsjkfnjshvlkzdjlfkmndsmvnoqsjflq;sjvmkjsfhdvjpfdjkbsfmmjqsd^fjksdlks");
        dto.setToken(at);
        return dto;
    }

    public static String toJson(UserDTO userDTO) throws JsonProcessingException {
        return map.writeValueAsString(userDTO);
    }

    public static User getSampleUser(){
        return userFactory.createUserModel(getSampleDTO());
    }

    public static VerificationToken getSampleVerificationToken(){
        VerificationToken vt = new VerificationToken();
        vt.setExpirydate(20);
        return vt;
    }
}
