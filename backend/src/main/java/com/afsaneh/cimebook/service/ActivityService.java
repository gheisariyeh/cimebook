package com.afsaneh.cimebook.service;

import com.afsaneh.cimebook.dto.ActivityResponse;
import com.afsaneh.cimebook.model.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {

    List<ActivityResponse> findAll();

    Optional<ActivityResponse> findById(Long id);
}