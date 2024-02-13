package org.ahala.examples.theserver.middleware;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.ahala.examples.theserver.services.security.ApiKeyPrincipal;
import org.ahala.examples.theserver.services.security.Principal;
import org.ahala.examples.theserver.services.security.SessionProvider;
import org.ahala.examples.theserver.services.security.SimpleClaims;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class ApiKeyAuthenticationFilter extends AuthenticationFilterBase {

    private final SessionProvider sessionProvider;

    private ApiKeyPrincipal fetchPrincipal(HttpServletRequest request) {
        String apiKey = request.getHeader("x-api-key");
        if (!StringUtils.hasText(apiKey))
            return null;

        return ApiKeyPrincipal.tryParse(apiKey);
    }

    @Override
    protected Claims fetchClaims(HttpServletRequest request) {
        try {
            ApiKeyPrincipal apiKeyPrincipal = fetchPrincipal(request);
            Principal principal = sessionProvider.verifyApiKey(apiKeyPrincipal);
            return new SimpleClaims(principal.getClaims());

        } catch (Exception e) {
            return null;

        }
    }
}
