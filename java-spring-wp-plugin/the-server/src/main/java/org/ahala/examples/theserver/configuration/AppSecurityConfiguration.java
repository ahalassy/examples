package org.ahala.examples.theserver.configuration;

import lombok.RequiredArgsConstructor;
import org.ahala.examples.theserver.components.JwtUtil;
import org.ahala.examples.theserver.middleware.ApiKeyAuthenticationFilter;
import org.ahala.examples.theserver.middleware.JwtAuthenticationFilter;
import org.ahala.examples.theserver.services.security.SessionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppSecurityConfiguration {

    private static final String[] POST_WHITE_LIST = new String[]{
            "/api/auth",
            "/api/user",
            "/api/user/key"
    };
    private final JwtUtil jwtUtil;

    private final SessionProvider sessionProvider;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter(jwtUtil);
    }

    @Bean
    public ApiKeyAuthenticationFilter apiKeyAuthenticationTokenFilterBean() throws Exception {
        return new ApiKeyAuthenticationFilter(sessionProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(jwtAuthenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(apiKeyAuthenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, POST_WHITE_LIST)
                        .permitAll()
                        .requestMatchers("/api/**")
                        .authenticated()
                        .anyRequest()
                        .permitAll()
                )
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
