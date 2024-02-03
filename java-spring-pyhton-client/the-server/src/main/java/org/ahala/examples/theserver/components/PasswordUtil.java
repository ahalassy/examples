package org.ahala.examples.theserver.components;

import org.springframework.stereotype.Component;

@Component
public interface PasswordUtil {

    String createHash(String password);

    boolean verifyHash(String hash, String password);

}
