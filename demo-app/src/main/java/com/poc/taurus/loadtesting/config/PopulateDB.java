package com.poc.taurus.loadtesting.config;

import com.poc.taurus.loadtesting.model.User;
import com.poc.taurus.loadtesting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class PopulateDB implements ApplicationRunner {

    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.deleteAll();

        log.info("[INFO] Deleting existing users.");
        log.info("[INFO] Populating MongoDB with fake users.");

        List<User> users = new ArrayList<>();
        IntStream.range(1, 10).forEach(id -> {
            User user = User.builder()
                    .name("name" + String.valueOf(id))
                    .bio("bio" + String.valueOf(id))
                    .age(id)
                    .build();
            users.add(user);
        });
        userRepository.saveAll(users);

        log.info("[SUCCESS] Application running with 99 fake users.");
        log.info("[INFO] Application started on port 8080");

    }
}
