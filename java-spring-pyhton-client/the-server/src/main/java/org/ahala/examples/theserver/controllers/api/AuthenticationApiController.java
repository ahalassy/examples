package org.ahala.examples.theserver.controllers.api;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.ahala.examples.theserver.AppException;
import org.ahala.examples.theserver.components.JwtUtil;
import org.ahala.examples.theserver.services.security.Credentials;
import org.ahala.examples.theserver.services.security.Principal;
import org.ahala.examples.theserver.services.security.SessionProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
