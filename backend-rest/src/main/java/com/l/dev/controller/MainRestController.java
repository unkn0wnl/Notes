package com.l.dev.controller;

import com.l.dev.entity.model.User;
import com.l.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/api")
public class MainRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getAllUsers() {
        return userService.findAll();
    }

}
