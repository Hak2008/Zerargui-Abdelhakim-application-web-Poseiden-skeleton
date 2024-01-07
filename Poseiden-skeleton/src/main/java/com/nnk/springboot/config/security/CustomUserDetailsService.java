package com.nnk.springboot.config.security;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

/**
 * CustomUserDetailsService implements the Spring Security UserDetailsService interface
 * to provide custom user details retrieval during authentication.
 * It loads user information from the UserRepository and creates a UserDetails object
 * to be used by Spring Security for authentication and authorization.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Autowired UserRepository for accessing user information from the database.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves user details from the UserRepository based on the provided username.
     *
     * @param username the username for which user details are to be loaded.
     * @return UserDetails containing user information.
     * @throws UsernameNotFoundException if the specified username is not found in the UserRepository.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user information from the UserRepository
        User user = userRepository.findByUsername(username);
        // Create a UserDetails object with the retrieved user information
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
    }

    /**
     * Returns a list of GrantedAuthority based on the user's role.
     *
     * @param role the role of the user.
     * @return a list of GrantedAuthority for the user.
     */
    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        // Create a SimpleGrantedAuthority with the "ROLE_" prefix
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}