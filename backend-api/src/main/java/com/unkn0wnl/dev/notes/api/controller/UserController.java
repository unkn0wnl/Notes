package com.unkn0wnl.dev.notes.api.controller;

import com.unkn0wnl.dev.notes.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

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

    @RequestMapping(value = "/user/isAvailable")
    public ResponseEntity<?> checkUserAvailability(@RequestParam String type, @RequestParam String value) {
        Boolean isAvailable = userService.checkUserAvailability(type, value);
        return ResponseEntity.ok(isAvailable);
    }

}
