package com.afsaneh.cimebook.dto;

import com.afsaneh.cimebook.model.ActivityCategory;
import com.afsaneh.cimebook.model.ActivityDifficulty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ActivityCreateRequest(
        @NotBlank
        @Size(max = 150)
        String title,

        @NotBlank
        @Size(max = 2000)
        String description,

        @NotNull
        ActivityCategory category,

        @NotNull
        ActivityDifficulty difficulty,

        @NotBlank
        @Size(max = 50)
        String duration,

        @NotNull
        @PositiveOrZero
        @Digits(integer = 6, fraction = 2)
        BigDecimal price,

        @NotBlank
        @Size(max = 500)
        String image
) {
}