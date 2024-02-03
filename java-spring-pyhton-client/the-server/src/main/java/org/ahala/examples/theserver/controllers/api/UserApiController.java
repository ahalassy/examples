package org.ahala.examples.theserver.controllers.api;

import lombok.RequiredArgsConstructor;
import org.ahala.examples.theserver.AppException;
import org.ahala.examples.theserver.controllers.api.model.response.UserResponse;
import org.ahala.examples.theserver.services.security.Credentials;
import org.ahala.examples.theserver.services.security.Impersonation;
import org.ahala.examples.theserver.services.security.UserProvider;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserProvider userProvider;

    @PostMapping()
    public UserResponse createUser(@ModelAttribute Credentials credentials) throws AppException {
        Impersonation impersonation = userProvider.createUser(credentials);
        return UserResponse.fromImpersonation(impersonation);

    }

    @GetMapping()
    public UserResponse getUser(@AuthenticationPrincipal UserDetails user) throws AppException {
        Impersonation impersonation = userProvider.findByEmail(user.getUsername());
        return UserResponse.fromImpersonation(impersonation);
    }

}
