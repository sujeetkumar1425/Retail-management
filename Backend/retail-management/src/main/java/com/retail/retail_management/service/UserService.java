package com.retail.retail_management.service;

import com.retail.retail_management.entity.User;
import com.retail.retail_management.entity.UserRole;
import com.retail.retail_management.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public User createUser(
            String name,
            String email,
            String password,
            UserRole role) {

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException(
                    "Email already registered"
            );
        }

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(
                passwordEncoder.encode(password)
        );
        user.setRole(
                role != null ? role : UserRole.STAFF
        );

        return userRepository.save(user);
    }

    public User updateUser(
            Long id,
            String name,
            String email) {

        User user = getUserById(id);

        user.setName(name);
        user.setEmail(email);

        return userRepository.save(user);
    }

    public User updateRole(
            Long id,
            UserRole role) {

        User user = getUserById(id);

        user.setRole(role);

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {

        User user = getUserById(id);

        userRepository.delete(user);
    }
}