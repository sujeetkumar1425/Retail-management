package com.retail.retail_management.controller;

import com.retail.retail_management.dto.UserResponse;
import com.retail.retail_management.entity.User;
import com.retail.retail_management.entity.UserRole;
import com.retail.retail_management.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers()
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                toResponse(userService.getUserById(id))
        );
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @RequestBody CreateUserRequest request) {

        User user = userService.createUser(
                request.name(),
                request.email(),
                request.password(),
                request.role()
        );

        return ResponseEntity.ok(toResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request) {

        User user = userService.updateUser(
                id,
                request.name(),
                request.email()
        );

        return ResponseEntity.ok(toResponse(user));
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<UserResponse> updateRole(
            @PathVariable Long id,
            @RequestBody UpdateRoleRequest request) {

        User user = userService.updateRole(
                id,
                request.role()
        );

        return ResponseEntity.ok(toResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    private UserResponse toResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public record CreateUserRequest(
            String name,
            String email,
            String password,
            UserRole role
    ) {}

    public record UpdateUserRequest(
            String name,
            String email
    ) {}

    public record UpdateRoleRequest(
            UserRole role
    ) {}
}