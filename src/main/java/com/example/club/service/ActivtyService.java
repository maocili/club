package com.example.club.service;

import com.example.club.domain.Activity;

public interface ActivtyService {

    public int addActivity(Activity activity);
    public Activity getActivityById(int id);
    public int updateActivityById(Activity activity);

}
