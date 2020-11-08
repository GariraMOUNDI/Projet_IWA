package org.projet_iwa.auth.api.model;

import org.keycloak.representations.AccessTokenResponse;

import java.util.List;

public class UserDTO {
    private Long user_id;
    private String username;
    private String first_name;
    private String last_name;
    private String password;
    private String confirm_pass;
    private String email;
    private String phone_number;
    private AccessTokenResponse token;

    public UserDTO(Long user_id, String username, String first_name, String last_name, String password, String email, String phone_number) {
        this.user_id = user_id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getConfirm_pass() {
        return confirm_pass;
    }

    public void setConfirm_pass(String confirm_pass) {
        this.confirm_pass = confirm_pass;
    }

    public AccessTokenResponse getToken() {
        return token;
    }

    public void setToken(AccessTokenResponse token) {
        this.token = token;
    }

    public UserResponseType checkFields(){
        if(username == null || first_name == null || last_name == null || email == null || password == null || phone_number == null)
            return UserResponseType.FIELD_NULL;
        else{
            if(username.length() < 4 || first_name.length() < 4 || last_name.length() < 4 || email.length() < 4 || password.length() < 4 || phone_number.length() < 4)
                return UserResponseType.FIELD_LENGTH;
        }
        if(!email.contains("@")){
            return UserResponseType.BAD_EMAIL;
        }else{
            String [] l_e = email.split("\\.");
            if(l_e.length < 2 || l_e[l_e.length - 1].contains("@"))
                return UserResponseType.BAD_EMAIL;
        }
        try{
            Integer.parseInt(phone_number);
        }catch (NumberFormatException e){
            return UserResponseType.BAD_PHONE_NUMBER;
        }
        if(!password.equals(confirm_pass))
            return UserResponseType.BAD_CONFIRMATION;

        return UserResponseType.VALID_USER;
    }
}

