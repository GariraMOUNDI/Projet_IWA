package org.projet_iwa.auth.api.config;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.projet_iwa.auth.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Service
public class KeycloakService {

    @Autowired
    private KeycloakInit keycloakInit;

    public void createKeycloakUser(User user){
        UserRepresentation keycloak_user = new UserRepresentation();
        keycloak_user.setUsername(user.getUsername());
        keycloak_user.setFirstName(user.getFirst_name());
        keycloak_user.setLastName(user.getLast_name());
        keycloak_user.setEmail(user.getEmail());
        keycloak_user.setEnabled(true);

        List<CredentialRepresentation> credentials = new ArrayList<>();
        CredentialRepresentation cr = new CredentialRepresentation();
        cr.setType(CredentialRepresentation.PASSWORD);
        cr.setValue(user.getPassword());
        cr.setTemporary(false);
        credentials.add(cr);
        keycloak_user.setCredentials(credentials);

        Response response = keycloakInit.getRealm().users().create(keycloak_user);
        String created_id = CreatedResponseUtil.getCreatedId(response);

        // Give a role to new users
//        RoleRepresentation user_role =
//                keycloakInit.getRoles()
//                        .get(keycloakInit.getKeycloak_user_role())
//                        .toRepresentation();

//        keycloakInit.getRealm()
//                .users()
//                .get(created_id)
//                .roles()
//                .clientLevel(keycloakInit.getKeycloak_resource())
//                .add(Collections.singletonList(user_role));
    }
}
