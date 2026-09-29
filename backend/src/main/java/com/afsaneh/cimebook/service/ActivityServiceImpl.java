package com.afsaneh.cimebook.service;

import com.afsaneh.cimebook.dto.ActivityResponse;
import com.afsaneh.cimebook.dto.ActivityWriteRequest;
import com.afsaneh.cimebook.model.Activity;
import com.afsaneh.cimebook.repository.ActivityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<ActivityResponse> findAll() {
        return activityRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public Optional<ActivityResponse> findById(Long id) {
        return activityRepository.findById(id)
                .map(this::toResponse);
    }

    private ActivityResponse toResponse(Activity activity) {
        return new ActivityResponse(
                activity.getId(),
                activity.getTitle(),
                activity.getDescription(),
                formatCategory(activity),
                formatDifficulty(activity),
                activity.getDuration(),
                activity.getPrice(),
                activity.getImage()
        );
    }

    private String formatCategory(Activity activity) {
        return switch (activity.getCategory()) {
            case HIKING -> "Hiking";
            case CLIMBING -> "Climbing";
            case CANYONING -> "Canyoning";
        };
    }

    private String formatDifficulty(Activity activity) {
        return switch (activity.getDifficulty()) {
            case BEGINNER -> "Beginner";
            case INTERMEDIATE -> "Intermediate";
            case ADVANCED -> "Advanced";
        };
    }

    @Override
    public ActivityResponse create(ActivityWriteRequest request) {
        Activity activity = new Activity(
                request.title(),
                request.description(),
                request.category(),
                request.difficulty(),
                request.duration(),
                request.price(),
                request.image()
        );

        Activity savedActivity = activityRepository.save(activity);

        return toResponse(savedActivity);
    }

    @Override
    public Optional<ActivityResponse> update(
            Long id,
            ActivityWriteRequest request
    ) {
        return activityRepository.findById(id)
                .map(activity -> {
                    activity.updateDetails(
                            request.title(),
                            request.description(),
                            request.category(),
                            request.difficulty(),
                            request.duration(),
                            request.price(),
                            request.image()
                    );

                    Activity savedActivity = activityRepository.save(activity);
                    return toResponse(savedActivity);
                });
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Optional<Activity> activity = activityRepository.findById(id);

        if (activity.isEmpty()) {
            return false;
        }

        activityRepository.delete(activity.get());
        return true;
    }
}