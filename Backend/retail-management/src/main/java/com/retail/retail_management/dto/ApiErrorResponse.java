package com.retail.retail_management.dto;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp
) {
}