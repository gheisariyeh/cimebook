package com.afsaneh.cimebook.controller;

import com.afsaneh.cimebook.dto.ActivityResponse;
import com.afsaneh.cimebook.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.afsaneh.cimebook.dto.ActivityCreateRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
            @Valid @RequestBody ActivityCreateRequest request
    ) {
        ActivityResponse created = activityService.create(request);

        URI location = URI.create("/activities/" + created.id());

        return ResponseEntity.created(location).body(created);
    }
}