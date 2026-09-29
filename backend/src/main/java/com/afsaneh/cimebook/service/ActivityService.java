package com.afsaneh.cimebook.service;

import com.afsaneh.cimebook.dto.ActivityResponse;
import com.afsaneh.cimebook.model.Activity;
import com.afsaneh.cimebook.dto.ActivityWriteRequest;

import java.util.List;
import java.util.Optional;

public interface ActivityService {

    List<ActivityResponse> findAll();

    Optional<ActivityResponse> findById(Long id);

    ActivityResponse create(ActivityWriteRequest request);

    Optional<ActivityResponse> update(Long id, ActivityWriteRequest request);

    boolean delete(Long id);
}