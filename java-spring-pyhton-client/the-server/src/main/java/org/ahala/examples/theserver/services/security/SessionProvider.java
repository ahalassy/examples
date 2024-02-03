package org.ahala.examples.theserver.services.security;

import com.noty.web.NotyAuthorizationException;
import com.noty.web.services.security.Credentials;

public interface SessionProvider {

    /**
     * Authenticates the user with the given credentials.
     * The implementation should focus on creating the principal object which will be used by other services to verify
     * the permissions.
     * @param credentials The credentials used to find and authenticate the user.
     * @return Returns the authentication principal object.
     * @throws NotyAuthorizationException When the authentication could not be done for some reason.
     */
    Principal authenticate(Credentials credentials) throws NotyAuthorizationException;
}
