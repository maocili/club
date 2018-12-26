package com.example.club.mapper;

import com.example.club.domain.Account;
import com.example.club.domain.Permission;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {


    @Select("SELECT account_info.id,account_info.username,account_info.student_id,permission.permission from account_info LEFT JOIN permission on permission.username = account_info.username where account_info.username = #{username} and password = #{password} AND permission.permission is not NULL")
    @Results({
            @Result(column = "student_id", property = "studentId")
    })
    Permission check(String username, String password);
}