package com.poc.taurus.loadtesting.service;

import com.poc.taurus.loadtesting.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User createUser(User user);
}
