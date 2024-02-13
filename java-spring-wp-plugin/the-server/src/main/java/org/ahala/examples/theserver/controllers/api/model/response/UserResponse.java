package org.ahala.examples.theserver.controllers.api.model.response;

import lombok.Builder;
import lombok.Data;
import org.ahala.examples.theserver.services.security.Impersonation;

@Builder
@Data
public class UserResponse {

    private long id;
    private String email;

    public static UserResponse fromImpersonation(Impersonation impersonation) {
        return UserResponse.builder()
                .email(impersonation.getEmail())
                .id(impersonation.getId())
                .build();
    }
}
