package com.l.dev.controller;

import com.l.dev.payload.request.LoginRequest;
import com.l.dev.payload.request.SingUpRequest;
import com.l.dev.payload.response.ApiResponse;
import com.l.dev.payload.response.JwtAuthenticationResponse;
import com.l.dev.repository.UserRepository;
import com.l.dev.security.provider.JwtTokenProvider;
import com.l.dev.service.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.apache.logging.log4j.LogManager.getLogger;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private static final Logger LOGGER;

    static {
        LOGGER = getLogger(AuthenticationController.class);
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;

    public AuthenticationController() {
        super();
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        LOGGER.info(loginRequest.toString());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingUpRequest singUpRequest) {
        if (userRepository.existsByUsername(singUpRequest.getUsername())) {
            LOGGER.info("Username {} already exist", singUpRequest.getUsername());
            return new ResponseEntity<>(
                    new ApiResponse(false, "Username already taken!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(singUpRequest.getEmail())) {
            LOGGER.info("Email {} already exist", singUpRequest.getEmail());
            return new ResponseEntity<>(
                    new ApiResponse(false, "Email already in use!"), HttpStatus.BAD_REQUEST);
        }
        userService.registerNewUser(singUpRequest.getName(),
                singUpRequest.getUsername(),
                singUpRequest.getEmail(),
                passwordEncoder.encode(singUpRequest.getPassword()));

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(singUpRequest.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User was registered successfully"));
    }

}