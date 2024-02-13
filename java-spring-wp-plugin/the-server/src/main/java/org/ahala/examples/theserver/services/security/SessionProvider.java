package org.ahala.examples.theserver.services.security;

import org.ahala.examples.theserver.AppException;

public interface SessionProvider {

    /**
     * Authenticates the user with the given credentials.
     * The implementation should focus on creating the principal object which will be used by other services to verify
     * the permissions.
     * @param credentials The credentials used to find and authenticate the user.
     * @return Returns the authentication principal object.
     * @throws AppException When the authentication could not be done for some reason.
     */
    Principal authenticate(Credentials credentials) throws AppException;

    Principal verifyApiKey(ApiKeyPrincipal principal) throws AppException;
}
