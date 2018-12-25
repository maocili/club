package com.example.club.service.impl;

import com.example.club.domain.Account;
import com.example.club.domain.Student;
import com.example.club.mapper.AccountMapper;
import com.example.club.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;


    @Override
    public int createAccount(Student student) {
        accountMapper.createAccount(student);
        int id = student.getId();
        return id;
    }

    @Override
    public String login(Account account) {
        Account account1=  accountMapper.login(account);

        if(account1 == null){
            return null;
        }else {
            String studentId = account1.getStudentId();
            return studentId;
        }
    }

    @Override
    public boolean isEntry(String studentId) {
        Account account = accountMapper.isEntry(studentId);
        return account==null;
    }


}
