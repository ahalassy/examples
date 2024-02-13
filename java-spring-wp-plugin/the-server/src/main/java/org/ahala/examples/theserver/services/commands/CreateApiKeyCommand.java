package org.ahala.examples.theserver.services.commands;

import lombok.RequiredArgsConstructor;
import org.ahala.examples.theserver.AppException;
import org.ahala.examples.theserver.services.security.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateApiKeyCommand {

    private final UserProvider userProvider;

    private final SessionProvider sessionProvider;

    private Credentials credentials;

    public CreateApiKeyCommand setCredentials(Credentials credentials) {
        this.credentials = credentials;

        return this;
    }

    public String execute() throws AppException {
        try {
            Principal principal = sessionProvider.authenticate(credentials);
            ApiKeyPrincipal keyPrincipal = userProvider.createApiKey(principal);

            return keyPrincipal.toString();

        } catch (Exception e) {
            throw new AppException("Failed to generate API key.", e);
        }

    }

}
