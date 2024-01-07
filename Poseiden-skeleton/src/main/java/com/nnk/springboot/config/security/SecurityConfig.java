package com.nnk.springboot.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;

/**
 * Configuration class for Spring Security settings.
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity instance to configure.
     * @return the configured SecurityFilterChain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Session management configuration
                .sessionManagement(sessionManagement -> {
                    sessionManagement
                            // Configures session creation policy
                            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                            // Migrates session fixation issues
                            .sessionFixation().migrateSession();
                })
                // URL authorization configuration
                .authorizeHttpRequests(auth -> {
                    // Permits all access to root and login
                    auth.requestMatchers( "/", "/app/login").permitAll();
                    // Requires ADMIN role for specific URLs
                    auth.requestMatchers("/user/**", "/app/secure/article-details").hasRole("ADMIN");
                    // Requires USER role for specific URLs
                    auth.requestMatchers("/trade/**", "/ruleName/**","/rating/**","/curvePoint/**","/bidList/**").hasRole("USER");
                    // Requires authentication for any other URL
                    auth.anyRequest().authenticated();
                })
                // Logout configuration
                .logout((logout) -> logout
                        // Permits all for logout URL
                        .permitAll()
                        // Configures logout request matcher
                        .logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
                )
                // Form login configuration
                .formLogin(form -> form
                        .successHandler((request, response, authentication) -> {
                            // Get the authenticated user's roles
                            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                            // Check user role and redirect accordingly
                            if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                                response.sendRedirect("/bidList/list");
                            } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                                response.sendRedirect("/user/list");
                            }
                        })
                )
                // CSRF(Cross-Site Request Forgery) protection disabled
                .csrf(csrf -> csrf.disable())
                // Exception handling configuration
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                // Redirects to /app/error on access denied
                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    response.sendRedirect("/app/error");
                                })
                )
                // Custom filter after successful authentication
                .addFilterAfter((request, response, chain) -> {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    if (authentication != null) {
                        log.info("Authenticated User: {}", authentication.getName());
                        log.info("Authorities: {}", authentication.getAuthorities());
                    }
                    chain.doFilter(request, response);
                }, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Provides a BCryptPasswordEncoder bean.
     *
     * @return a BCryptPasswordEncoder instance.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an AuthenticationManager bean.
     *
     * @param http the HttpSecurity instance.
     * @param bCryptPasswordEncoder the BCryptPasswordEncoder instance.
     * @return an AuthenticationManager instance.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        // Configuring user details service and password encoder
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
}

