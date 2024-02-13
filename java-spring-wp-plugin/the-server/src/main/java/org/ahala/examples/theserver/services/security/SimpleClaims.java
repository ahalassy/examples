package org.ahala.examples.theserver.services.security;

import io.jsonwebtoken.Claims;

import java.time.Instant;
import java.util.*;

public class SimpleClaims extends HashMap<String, Object> implements Claims {

    public SimpleClaims(Map<String, String> source) {
        super(source);
    }

    @Override
    public String getIssuer() {
        return (String) get(ISSUER);
    }

    @Override
    public String getSubject() {
        return (String) get(SUBJECT);
    }

    @Override
    public Set<String> getAudience() {
        return new HashSet<>();
    }

    @Override
    public Date getExpiration() {
        return Date.from(Instant.MAX);
    }

    @Override
    public Date getNotBefore() {
        return Date.from(Instant.MIN);
    }

    @Override
    public Date getIssuedAt() {
        return Date.from(Instant.MIN);
    }

    @Override
    public String getId() {
        return (String) get(ID);
    }

    @Override
    public <T> T get(String claimName, Class<T> requiredType) {
        return requiredType.cast(get(claimName));
    }
}
