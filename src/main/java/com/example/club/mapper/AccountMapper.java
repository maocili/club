package com.example.club.mapper;

import com.example.club.domain.Account;
import com.example.club.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountMapper {

    @Insert("insert into account_info(username, password, student_id) value(#{username},#{password},#{studentId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int createAccount(Student student);

    //登陆查询
    @Select("select * from account_info where username= #{username} and password= #{password}")
    @Results({
            @Result(column = "student_id", property = "studentId")  //javaType = java.util.Date.class
    })
    Account login(Account account);

    //获取学生信息
    @Select("select * from student_info where student_id=#{studentId}")
    @Results({
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "department_name", property = "departmentName"),
            @Result(column = "student_name", property = "studentName"),
            @Result(column = "class_name", property = "className")
    })
    Student getStudent(String studentId);

    //查看学号是否录入
    @Select("select * from account_info where student_id = #{studentId}")
    Account isEntry(String studentId);


    //更新学生信息
    @Update("update student_info set department_name=#{departmentName},student_name=#{studentName},class_name=#{className} where student_id=#{studentId}")
    void updateStudentById(Student student);
    
    
    //查询所有的账号信息
//    @Select("SELECT * FROM account_info")
//    @Results({
//            @Result(column = "student_id", property = "studentId")  //javaType = java.util.Date.class
//    })
//    List<Account> getAll();
//
//    //根据id查找账号信息
//    @Select("SELECT * FROM account_info WHERE id = #{id}")
//    @Results({
//            @Result(column = "student_id", property = "studentId")
//    })
//    Account findById(int id);
//
//
//
//    @Update("UPDATE account_info SET password=#{password} WHERE username =#{username}")
//    void update(Account account);
//
//    @Delete("DELETE FROM account_info WHERE student_id =#{student_id}")
//    void delete(String student_id);

}
