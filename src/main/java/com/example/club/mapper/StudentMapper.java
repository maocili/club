package com.example.club.mapper;

import com.example.club.domain.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentMapper {

    @Insert("Insert into student_info values(#{studentId},#{departmentName},#{studentName},#{className})")
    int addStudent(Student student);

    @Select("select * from student_info")
    @Results({
            @Result(property = "studentId",column = "student_id"),
            @Result(property = "departmentName",column = "department_name"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "className",column = "class_name"),
    })
    List<Student> getAllStudent();
}
