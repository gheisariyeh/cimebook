package com.afsaneh.cimebook.controller;

import com.afsaneh.cimebook.dto.ActivityResponse;
import com.afsaneh.cimebook.dto.ActivityWriteRequest;
import com.afsaneh.cimebook.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<ActivityResponse> getAllActivities() {
        return activityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponse> getActivityById(
            @PathVariable Long id
    ) {
        return activityService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(
            @Valid @RequestBody ActivityWriteRequest request
    ) {
        ActivityResponse created = activityService.create(request);

        URI location = URI.create("/activities/" + created.id());

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponse> updateActivity(
            @PathVariable Long id,
            @Valid @RequestBody ActivityWriteRequest request
    ) {
        return activityService.update(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}