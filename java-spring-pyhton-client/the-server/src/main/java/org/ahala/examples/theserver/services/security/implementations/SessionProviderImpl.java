package org.ahala.examples.theserver.services.security.implementations;

import lombok.AllArgsConstructor;
import org.ahala.examples.theserver.AppException;
import org.ahala.examples.theserver.components.PasswordUtil;
import org.ahala.examples.theserver.entities.AppUser;
import org.ahala.examples.theserver.services.security.Credentials;
import org.ahala.examples.theserver.services.security.Principal;
import org.ahala.examples.theserver.services.security.SessionProvider;
import org.ahala.examples.theserver.services.security.UserProvider;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class SessionProviderImpl implements SessionProvider {

    private final UserProvider userProvider;

    private final PasswordUtil passwordUtil;
    
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

}
