package com.common.sample.token.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class SecurityContextHolder
{
    private Object credential;
    private String subject;
    private String issuer;
    private String accessToken;
    private Date expiration;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Object getCredential() {
        return credential;
    }

    public void setCredential(Object credential) {
        this.credential = credential;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "{" +
                "\"credential\": \"" + credential  + "\"," +
                "\"subject\": \"" + subject  + "\"," +
                "\"issuer\": \"" + issuer  + "\"," +
                "\"accessToken\": \"" + accessToken  + "\"," +
                "\"expiration\": \"" + expiration.toString()  + "\"," +
                "}";
    }
}