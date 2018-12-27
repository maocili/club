package com.example.club.service.impl;

import com.example.club.domain.Enter;
import com.example.club.mapper.EnterMapper;
import com.example.club.service.EnterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterServiceImpl implements EnterService {

    @Autowired
    EnterMapper enterMapper;

    @Autowired
    EnterService enterService;

    @Override
    public int enterActivty(Enter enter) {
        int lines = enterMapper.enterActivty(enter);
        return lines;
    }

    @Override
    public List<Enter> getAll() {
        return enterMapper.getAll();
    }
}
