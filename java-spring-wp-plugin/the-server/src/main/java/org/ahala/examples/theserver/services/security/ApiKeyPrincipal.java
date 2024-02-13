package org.ahala.examples.theserver.services.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Base64;

@Data
@Builder
@AllArgsConstructor
public class ApiKeyPrincipal {

    private long id;
    private String key;

    public static ApiKeyPrincipal parse(@NonNull String key) {
        String raw = new String(Base64.getDecoder().decode(key));
        String[] parts = raw.split(":");
        if (parts.length != 2)
            throw new IllegalArgumentException("The provided key is not an API key.");

        return new ApiKeyPrincipal(
                Long.parseLong(parts[0]),
                parts[1]
        );
    }

    public static ApiKeyPrincipal tryParse(String apiKey) {
        try {
            return ApiKeyPrincipal.parse(apiKey);
        } catch (Exception e) {
            return null;
        }
    }

    public String toString() {
        String raw = String.format("%s:%s", id, key);
        return Base64.getEncoder().encodeToString(raw.getBytes());
    }

}
