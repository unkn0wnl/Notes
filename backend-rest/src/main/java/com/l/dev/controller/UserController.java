package com.l.dev.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

//    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/user/me", method = RequestMethod.GET)
    public UserDetails getCurrentUserInfo(@AuthenticationPrincipal UserDetails currentUser) {
        return currentUser;
    }

}
