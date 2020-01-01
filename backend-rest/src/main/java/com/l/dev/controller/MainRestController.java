package com.l.dev.controller;

import com.l.dev.entity.model.User;
import com.l.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class MainRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

}
