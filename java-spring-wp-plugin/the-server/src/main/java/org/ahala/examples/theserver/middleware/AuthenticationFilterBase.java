package org.ahala.examples.theserver.middleware;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ahala.examples.theserver.services.security.Impersonation;
import org.ahala.examples.theserver.util.RequestUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public abstract class AuthenticationFilterBase extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        applyAuthentication(request);

        filterChain.doFilter(request, response);
    }

    protected void applyAuthentication(HttpServletRequest request) {
        Claims claims = fetchClaims(request);
        if (claims == null)
            return;

        Impersonation user = Impersonation.fromClaims(claims);

        WebAuthenticationDetails details = new WebAuthenticationDetailsSource()
                .buildDetails(request);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                List.of(new SimpleGrantedAuthority("user"))
        );
        authentication.setDetails(details);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Retrieves the claims from the given request. This method shall not throw any exceptions, just return null, if the
     * operation was unsuccessful.
     * @param request The request from the claims must be fetched.
     * @return The fetched claims when fetching was successful, null, otherwise.
     */
    protected abstract Claims fetchClaims(HttpServletRequest request);
}
