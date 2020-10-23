package org.projet_iwa.auth.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class User {
    private String user_id;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Id
    public String getUser_id() {
        return user_id;
    }
}
