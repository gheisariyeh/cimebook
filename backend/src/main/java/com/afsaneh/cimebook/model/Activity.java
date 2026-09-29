package com.afsaneh.cimebook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String title;

    @NotBlank
    @Size(max = 2000)
    @Column(nullable = false, length = 2000)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ActivityCategory category;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ActivityDifficulty difficulty;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String duration;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 6, fraction = 2)
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal price;

    @NotBlank
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String image;

    protected Activity() {
    }

    public Activity(
            String title,
            String description,
            ActivityCategory category,
            ActivityDifficulty difficulty,
            String duration,
            BigDecimal price,
            String image
    ) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.difficulty = difficulty;
        this.duration = duration;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ActivityCategory getCategory() {
        return category;
    }

    public ActivityDifficulty getDifficulty() {
        return difficulty;
    }

    public String getDuration() {
        return duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}