package com.poc.taurus.loadtesting.controller;

import com.poc.taurus.loadtesting.model.User;
import com.poc.taurus.loadtesting.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        log.info("[INFO] Users sent.");
        return userService.findAllUsers();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        return new ResponseEntity<>(userService.createUser(newUser), HttpStatus.CREATED);
    }
}
