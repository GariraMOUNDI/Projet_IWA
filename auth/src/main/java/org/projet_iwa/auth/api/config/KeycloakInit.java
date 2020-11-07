package org.projet_iwa.auth.api.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakInit {

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

    public String getKeycloak_url() {
        return keycloak_url;
    }

    public String getKeycloak_realm() {
        return keycloak_realm;
    }

    public String getKeycloak_resource() {
        return keycloak_resource;
    }

    public String getKeycloak_username() {
        return keycloak_username;
    }

    public String getKeycloak_password() {
        return keycloak_password;
    }

    public String getKeycloak_user_role() {
        return keycloak_user_role;
    }

    public RealmResource getRealm(){
        if(realmResource == null){
            Keycloak keycloak =
                    Keycloak.getInstance(keycloak_url, "master", keycloak_username, keycloak_password, "admin-cli");
            realmResource = keycloak.realm(keycloak_realm);
        }
        return realmResource;
    }

    public RolesResource getRoles(){
        if(realmResource == null)
            getRealm();

        if(roles == null)
            roles = realmResource.clients().get(keycloak_resource).roles();

        return roles;
    }
}
