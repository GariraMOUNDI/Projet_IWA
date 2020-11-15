package org.projet_iwa.auth.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Date;

@Entity(name="verif_tokens")
@Access(AccessType.FIELD)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class VerificationToken {
    public static final int EXPIRATION = 60 * 24; // nb min

    @Id
    private String token;
    private Long user_id;
    private Date expirydate;


    public VerificationToken() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,expiryTimeInMinutes);
        this.expirydate = cal.getTime();
    }
}
