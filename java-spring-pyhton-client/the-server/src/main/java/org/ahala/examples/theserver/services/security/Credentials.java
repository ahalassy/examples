package org.ahala.examples.theserver.services.security;

import lombok.Data;
import org.ahala.examples.theserver.framework.model.Validatable;
import org.springframework.util.StringUtils;

@Data
public class Credentials implements Validatable {

    private String email;
    private String password;

    @Override
    public boolean isValid() {
        return StringUtils.hasText(email)
                && StringUtils.hasText(password);
    }
}
