package com.afsaneh.cimebook.dto;

import java.math.BigDecimal;

public record ActivityResponse(
        Long id,
        String title,
        String description,
        String category,
        String difficulty,
        String duration,
        BigDecimal price,
        String image
) {
}