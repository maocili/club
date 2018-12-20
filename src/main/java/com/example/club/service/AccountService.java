package com.example.club.service;

import com.example.club.domain.Account;
import com.example.club.domain.Student;

public interface AccountService {

    public int createAccount(Student student);
    public String login(Account account);
    public boolean isEntry(String studentId);

}
