package org.ahala.examples.theserver.services.security;

import lombok.Builder;
import lombok.Getter;
import org.ahala.examples.theserver.entities.AppUser;

import java.util.Map;

@Getter
@Builder
public class Principal {

    Map<String, String> claims;
    private AppUser user;

}
