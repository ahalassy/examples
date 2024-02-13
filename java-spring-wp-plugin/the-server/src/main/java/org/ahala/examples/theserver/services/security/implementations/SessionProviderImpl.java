package org.ahala.examples.theserver.services.security.implementations;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.ahala.examples.theserver.AppException;
import org.ahala.examples.theserver.components.PasswordUtil;
import org.ahala.examples.theserver.entities.ApiKey;
import org.ahala.examples.theserver.entities.AppUser;
import org.ahala.examples.theserver.repositories.ApiKeyRepository;
import org.ahala.examples.theserver.services.security.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class SessionProviderImpl implements SessionProvider {

    private final UserProvider userProvider;

    private final PasswordUtil passwordUtil;

    private final ApiKeyRepository apiKeyRepository;

    public Principal authenticate(Credentials credentials) throws AppException {
        AppUser user = userProvider.findUserByCredentials(credentials);
        if (user == null)
            throw new AppException("User not found.");

        if (!passwordUtil.verifyHash(user.getToken(), credentials.getPassword()))
            throw new AppException("Invalid password.");

        return Principal.builder()
                .user(user)
                .claims(new HashMap<>())
                .build();
    }

    @Override
    public Principal verifyApiKey(ApiKeyPrincipal principal) throws AppException {
        ApiKey apiKey = apiKeyRepository.findById(principal.getId());
        if (!passwordUtil.verifyHash(apiKey.getToken(), principal.getKey()))
            throw new AppException("Invalid api key.");

        AppUser user = userProvider.findById(apiKey.getId());
        if (user == null)
            throw new AppException("Invalid api key.");

        return Principal.builder()
                .user(user)
                .claims(new HashMap<>() {{
                    put(Claims.ID, String.valueOf(user.getId()));
                    put(Claims.SUBJECT, user.getEmail());
                }})
                .build();
    }

}
