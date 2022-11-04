package com.poc.taurus.loadtesting.repository;

import com.poc.taurus.loadtesting.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
