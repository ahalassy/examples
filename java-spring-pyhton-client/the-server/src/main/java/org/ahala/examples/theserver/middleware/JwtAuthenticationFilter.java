package org.ahala.examples.theserver.middleware;

import com.noty.web.components.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@AllArgsConstructor
public class JwtAuthenticationFilter extends AuthenticationFilterBase {

    private static final Pattern pattern = Pattern.compile("-?\\d+");

    private JwtUtil jwtUtil;

    private String getJwtToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token))
            return null;

        return token.startsWith("Bearer")
                ? token.substring("Bearer ".length())
                : null;

    }

    @Override
    protected Claims fetchClaims(HttpServletRequest request) {
        String jwt = getJwtToken(request);
        return tryDecodeJwt(jwt);
    }

    private Claims tryDecodeJwt(String jwt) {
        if (jwt == null) {
            logger.debug("JWT bearer token is empty for the request.");
            return null;
        }

        Claims claims = jwtUtil.decode(jwt);
        if (!pattern.matcher(claims.getId()).matches())
            return null;

        return claims;
    }
}
