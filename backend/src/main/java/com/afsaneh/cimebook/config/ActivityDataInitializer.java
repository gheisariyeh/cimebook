package com.afsaneh.cimebook.config;

import com.afsaneh.cimebook.model.Activity;
import com.afsaneh.cimebook.model.ActivityCategory;
import com.afsaneh.cimebook.model.ActivityDifficulty;
import com.afsaneh.cimebook.repository.ActivityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ActivityDataInitializer implements CommandLineRunner {

    private final ActivityRepository activityRepository;

    public ActivityDataInitializer(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void run(String... args) {
        if (activityRepository.count() > 0) {
            return;
        }

        Activity hiking = new Activity(
                "Hiking at Semnoz",
                "A beginner-friendly hike with panoramic views over Lake Annecy and the surrounding mountains.",
                ActivityCategory.HIKING,
                ActivityDifficulty.BEGINNER,
                "4h",
                new BigDecimal("45.00"),
                "images/activities/hiking-semnoz.png"
        );

        Activity climbing = new Activity(
                "Climbing in Talloires",
                "Discover climbing routes near Lake Annecy with a certified local guide.",
                ActivityCategory.CLIMBING,
                ActivityDifficulty.INTERMEDIATE,
                "3h",
                new BigDecimal("60.00"),
                "images/activities/climbing-talloires.png"
        );

        Activity canyoning = new Activity(
                "Canyoning near Angon",
                "A refreshing canyoning experience for people looking for a more dynamic outdoor adventure.",
                ActivityCategory.CANYONING,
                ActivityDifficulty.INTERMEDIATE,
                "Half-day",
                new BigDecimal("75.00"),
                "images/activities/canyoning-angon.png"
        );

        activityRepository.saveAll(
                List.of(hiking, climbing, canyoning)
        );
    }
}