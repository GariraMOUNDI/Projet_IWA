package org.projet_iwa.auth.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.representations.AccessTokenResponse;
import org.projet_iwa.auth.api.model.UserDTO;

public class SampleData {

    private static final ObjectMapper map = new ObjectMapper();

    public static UserDTO getSampleDTO(){
        UserDTO dto = new UserDTO(
                null,
                "parti",
                "parti",
                "parti",
                "parti",
                "parti@gazert.com",
                "1548697",
                false
        );
        dto.setConfirm_pass("parti");
        AccessTokenResponse at = new AccessTokenResponse();
        at.setToken("qsflkqhslkfjqhsjkfnjshvlkzdjlfkmndsmvnoqsjflq;sjvmkjsfhdvjpfdjkbsfmmjqsd^fjksdlks");
        dto.setToken(at);
        return dto;
    }

    public static String toJson(UserDTO s) throws JsonProcessingException {
        return map.writeValueAsString(s);
    }
}
