package com.unkn0wnl.dev.notes.api.controller;

import com.unkn0wnl.dev.notes.api.exception.BadRequestException;
import com.unkn0wnl.dev.notes.core.service.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

import static org.apache.logging.log4j.LogManager.getLogger;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger LOGGER;

    static {
        LOGGER = getLogger(UserController.class);
    }

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user/me", method = RequestMethod.GET)
    public UserDetails getCurrentUserInfo(@AuthenticationPrincipal UserDetails currentUser) {
        return currentUser;
    }

    @RequestMapping(value = "/user/isAvailable", method = RequestMethod.GET)
    public ResponseEntity<?> checkUserAvailability(@RequestParam String type, @RequestParam String value) {
        LOGGER.debug("Type: {}\nValue: {}", type, value);
        Boolean isAvailable;

        try {
            isAvailable = !userService.checkUserAvailability(type, value);
        } catch (IllegalArgumentException ex) {
            LOGGER.warn(ex);
            throw new BadRequestException(ex);
        }

        return ResponseEntity.ok(new Properties() {
            {
                setProperty("isAvailable", isAvailable.toString());
            }
        });
    }

}