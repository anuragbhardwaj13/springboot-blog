package dev.anurag.blogapp.controller;

import dev.anurag.blogapp.repository.UserRepository;
import dev.anurag.blogapp.secuity.jwt.AuthTokenFilter;
import dev.anurag.blogapp.secuity.service.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/test")
    public String test(){

        return "Validated";
    }
    @GetMapping("/test/mod")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String testMod(){

        return "Mod Validated";
    }

    @GetMapping("/test/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testAdmin(){
        return "Admin Validated";
    }
    @GetMapping("/test/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String testUser(){
        return "User Validated";
    }
    @GetMapping("/user/name")
    public String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String username=userDetails.getUsername();
        logger.info(userRepository.findUserByUsername(username).getUsername());
        logger.info(userRepository.findUserByUsername(username).getEmail());
        return "Authenticated user's name: " + username;
    }
}
