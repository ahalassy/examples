package org.ahala.examples.theserver.services.security.implementations;

import lombok.AllArgsConstructor;
import org.ahala.examples.theserver.AppException;
import org.ahala.examples.theserver.components.DateTime;
import org.ahala.examples.theserver.components.PasswordUtil;
import org.ahala.examples.theserver.entities.ApiKey;
import org.ahala.examples.theserver.entities.AppUser;
import org.ahala.examples.theserver.repositories.ApiKeyRepository;
import org.ahala.examples.theserver.repositories.UserRepository;
import org.ahala.examples.theserver.services.security.*;
import org.ahala.examples.theserver.util.RandomUtil;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserProviderImpl implements UserProvider {

    public static final int API_KEY_RAW_LENGTH = 22;

    private final DateTime dateTime;

    private final PasswordUtil passwordUtil;

    private final UserRepository userRepository;

    private final ApiKeyRepository apiKeyRepository;

    @Override
    public Impersonation createUser(Credentials credentials) throws AppException {
        if (credentials == null)
            throw new IllegalArgumentException("Credentials must not be null.");

        if (!credentials.isValid())
            throw new AppException("Invalid credentials supplied.");

        String token = passwordUtil.createHash(credentials.getPassword());
        AppUser user = new AppUser(
                credentials.getEmail(),
                token,
                dateTime.now()
        );

        userRepository.save(user);

        return Impersonation.fromUser(user);
    }

    @Override
    public Impersonation findByEmail(String email) throws AppException {
        AppUser user = userRepository.findUserByEmail(email);
        if (user == null)
            throw new AppException("User was not found by e-mail address.");

        return Impersonation.fromUser(user);
    }

    @Override
    public AppUser findUserByCredentials(Credentials credentials) {
        return userRepository.findUserByEmail(credentials.getEmail());

    }

    @Override
    public AppUser findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public AppUser fromDetails(Impersonation impersonation) {
        return impersonation == null
                ? null
                : findById(impersonation.getId());
    }

    @Override
    public ApiKeyPrincipal createApiKey(Principal principal) throws AppException {
        String key = RandomUtil.generateString(API_KEY_RAW_LENGTH);

        ApiKey apiKey = ApiKey.builder()
                .createdAt(dateTime.now())
                .token(passwordUtil.createHash(key))
                .user(principal.getUser())
                .build();

        apiKeyRepository.save(apiKey);

        return ApiKeyPrincipal.builder()
                .id(apiKey.getId())
                .key(key)
                .build();
    }
}
