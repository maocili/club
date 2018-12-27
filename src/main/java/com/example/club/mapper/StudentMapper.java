package com.example.club.mapper;

import com.example.club.domain.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentMapper {

    @Insert("Insert into student_info values(#{studentId},#{departmentName},#{studentName},#{className})")
    int addStudent(Student student);
//    SELECT * FROM activity order by id ASC LIMIT #{lines} OFFSET #{start}
    @Select("SELECT * FROM student_info LIMIT #{lines} OFFSET #{start}")
    @Results({
            @Result(property = "studentId",column = "student_id"),
            @Result(property = "departmentName",column = "department_name"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "className",column = "class_name"),
    })
    List<Student> getStudent(int lines,int start);

    @Select("select * from student_info")
    @Results({
            @Result(property = "studentId",column = "student_id"),
            @Result(property = "departmentName",column = "department_name"),
            @Result(property = "studentName",column = "student_name"),
            @Result(property = "className",column = "class_name"),
    })
    List<Student> getAllStudent();

    //获取学生信息
    @Select("select * from student_info where student_id=#{studentId}")
    @Results({
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "department_name", property = "departmentName"),
            @Result(column = "student_name", property = "studentName"),
            @Result(column = "class_name", property = "className")
    })
    Student getStudentById(String studentId);

    @Delete("DELETE FROM student_info WHERE studentId =#{studentId}")
    int deleteStudentById(String studentId);

}
