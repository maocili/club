package com.example.club.service;

import com.example.club.domain.Enter;

import java.util.List;

public interface EnterService {
    public int enterActivty(Enter enter);
    public List<Enter> getAll();
    public boolean isSuccess(int activityId,String studentId);
}
