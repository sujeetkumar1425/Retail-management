package com.retail.retail_management.controller;

import com.retail.retail_management.dto.DashboardSummary;
import com.retail.retail_management.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummary> getSummary() {

        return ResponseEntity.ok(
                dashboardService.getSummary()
        );
    }
}