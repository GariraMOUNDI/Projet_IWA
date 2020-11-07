package org.projet_iwa.auth.api.model;

public class UserResponse extends Response<UserResponseType, UserDTO> {

    public UserResponse(UserResponseType userResponseType, UserDTO userDTO) {
        super(userResponseType, userDTO);
    }
}
