package org.ahala.examples.theserver.services.security;

import org.ahala.examples.theserver.AppException;
import org.ahala.examples.theserver.entities.AppUser;

public interface UserProvider {

    Impersonation createUser(Credentials credentials) throws AppException;

    Impersonation findByEmail(String email) throws AppException;

    AppUser findUserByCredentials(Credentials credentials);

    AppUser findById(long id);

    AppUser fromDetails(Impersonation details);

    ApiKeyPrincipal createApiKey(Principal principal) throws AppException;

}
