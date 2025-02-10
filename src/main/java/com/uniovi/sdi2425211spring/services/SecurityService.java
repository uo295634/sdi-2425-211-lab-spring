package com.uniovi.sdi2425211spring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
@Service
public class SecurityService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public SecurityService(AuthenticationManager authenticationManager, UserDetailsService
            userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }
    public String findLoggedInDni() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    public void autoLogin(String dni, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(dni);

        UsernamePasswordAuthenticationToken aToken = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(aToken);
        if (aToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(aToken);
            logger.debug(String.format("Auto login %s successfully!", dni));
        }
    }
}

