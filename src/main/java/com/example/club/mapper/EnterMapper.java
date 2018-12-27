package com.example.club.mapper;

import com.example.club.domain.Enter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EnterMapper {

    @Insert("INSERT into enter values (null,#{activityId},#{studentId})")
    int enterActivty(Enter enter);

    @Select("SELECT * FROM enter\n" +
            "INNER JOIN student_info ON enter.student_id = student_info.student_id ")
    @Results({
            @Result(property = "studentId",column = "student_id"),
            @Result(property = "activityId",column = "activity_id"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "departmentName",column = "department_name"),
            @Result(property = "className",column = "class_name")
    })
    List<Enter>getAll();
}
