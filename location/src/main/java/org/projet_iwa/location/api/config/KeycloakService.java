//package org.projet_iwa.location.api.config;
//
//import org.keycloak.representations.AccessTokenResponse;
//import org.keycloak.representations.idm.CredentialRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.projet_iwa.auth.api.model.UserDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.ws.rs.core.Response;
//import java.util.Collections;
//
//@Service
//public class KeycloakService {
//
//    @Autowired
//    private KeycloakInit keycloakInit;
//
//    public void createKeycloakUser(UserDTO userDTO){
//        UserRepresentation keycloak_user = new UserRepresentation();
//        keycloak_user.setUsername(userDTO.getUsername());
//        keycloak_user.setFirstName(userDTO.getFirst_name());
//        keycloak_user.setLastName(userDTO.getLast_name());
//        keycloak_user.setEmail(userDTO.getEmail());
//        keycloak_user.setEnabled(true);
//
//        keycloak_user.setCredentials(Collections.singletonList(getCredentials(userDTO.getPassword())));
//
//        Response response = keycloakInit.getRealm().users().create(keycloak_user);
////        String created_id = CreatedResponseUtil.getCreatedId(response);
//
//        // Give a role to new users
////        RoleRepresentation user_role =
////                keycloakInit.getRoles()
////                        .get(keycloakInit.getKeycloak_user_role())
////                        .toRepresentation();
//
////        keycloakInit.getRealm()
////                .users()
////                .get(created_id)
////                .roles()
////                .clientLevel(keycloakInit.getKeycloak_resource())
////                .add(Collections.singletonList(user_role));
//    }
//
//    public AccessTokenResponse getToken(String username, String password) {
//        return keycloakInit.getUserToken(username, password);
//    }
//
//    public void updateKeycloakUserCredentials(UserDTO userDTO){
//        UserRepresentation user = keycloakInit.getRealm().users()
//                .search(userDTO.getUsername())
//                .get(0);
//
//        keycloakInit.getRealm()
//                .users()
//                .get(user.getId())
//                .remove();
//
//        createKeycloakUser(userDTO);
//    }
//
//    private CredentialRepresentation  getCredentials(String password){
//        CredentialRepresentation cr = new CredentialRepresentation();
//        cr.setType(CredentialRepresentation.PASSWORD);
//        cr.setValue(password);
//        cr.setTemporary(false);
//        return cr;
//    }
//}
