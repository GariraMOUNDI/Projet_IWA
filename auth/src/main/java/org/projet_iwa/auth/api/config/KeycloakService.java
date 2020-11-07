package org.projet_iwa.auth.api.config;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.projet_iwa.auth.api.model.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class KeycloakService {
    @Value("${keycloak.auth-server-url}")
    private String keycloak_url;

    @Value("${keycloak.realm}")
    private String keycloak_realm;

    @Value("${keycloak.resource}")
    private String keycloak_resource;

    @Value("${covid-alert.keycloak.admin-username}")
    private String keycloak_username;

    @Value("${covid-alert.keycloak.admin-password}")
    private String keycloak_password;

    @Value("${covid-alert.keycloak.user-role}")
    private String keycloak_user_role;

    private RealmResource realmResource;
    private RolesResource roles;


    public void createKeycloakUser(UserDTO user){
        Keycloak keycloak =
                Keycloak.getInstance(keycloak_url, "master", keycloak_username, keycloak_password, "admin-cli");
        realmResource = keycloak.realm(keycloak_realm);
        roles = realmResource.clients().get(keycloak_resource).roles();
        RoleRepresentation d = roles.get("user").toRepresentation();


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

//        Map<String, List<String>> attributes = new HashMap<>();
//        List<String> list = new ArrayList<>();
//        list.add("Music");
//        list.add("Art");
//        attributes.put("hobby", list);
//        keycloak_user.setAttributes(attributes);

        Response response = realmResource.users().create(keycloak_user);
        String created_id = CreatedResponseUtil.getCreatedId(response);

        RoleRepresentation user_role = roles.get(keycloak_user_role).toRepresentation();
        realmResource
                .users()
                .get(created_id)
                .roles()
                .clientLevel(keycloak_resource)
                .add(Collections.singletonList(user_role));
    }
}
