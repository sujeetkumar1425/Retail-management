package com.retail.retail_management.dto;

import com.retail.retail_management.entity.UserRole;

public record UserResponse(
        Long id,
        String name,
        String email,
        UserRole role
) {
}