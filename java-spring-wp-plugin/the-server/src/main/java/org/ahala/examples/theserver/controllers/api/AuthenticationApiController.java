package org.ahala.examples.theserver.controllers.api;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.ahala.examples.theserver.AppException;
import org.ahala.examples.theserver.components.JwtUtil;
import org.ahala.examples.theserver.services.security.Credentials;
import org.ahala.examples.theserver.services.security.Principal;
import org.ahala.examples.theserver.services.security.SessionProvider;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationApiController {

    private final SessionProvider sessionProvider;

    private final JwtUtil jwtUtil;

    @PostMapping("/auth")
    public String Authenticate(
            @ModelAttribute Credentials credentials
    ) throws AppException {
        if (credentials == null || !credentials.isValid())
            throw new AppException("Insufficient credentials.");

        Principal principal = sessionProvider.authenticate(credentials);

        return jwtUtil.encode(principal);
    }

}
