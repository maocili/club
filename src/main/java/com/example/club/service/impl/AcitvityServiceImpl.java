package com.example.club.service.impl;

import com.example.club.domain.Activity;
import com.example.club.mapper.ActivityMapper;
import com.example.club.service.ActivtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.zip.DataFormatException;

@Service
public class AcitvityServiceImpl implements ActivtyService {

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 前端做验证判断是否为空
     * @param activity
     * @return
     * code -1 失败
     *      2 长度过长
     *      0 正常
     */
    @Override
    public int addActivity(Activity activity) {

        if (activity == null) {
            return -1;
        }
        try {
            activityMapper.addActivity(activity);
            return 0;
        }
        catch (DataIntegrityViolationException e)
        {
            return 2;
        }
    }

    @Override
    public Activity getActivityById(int id) {
        Activity activity = activityMapper.getById(id);
        return activity;
    }
}
