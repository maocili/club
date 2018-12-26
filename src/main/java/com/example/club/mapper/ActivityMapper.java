package com.example.club.mapper;

import com.example.club.domain.Activity;
import com.example.club.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ActivityMapper {

    @Select("select * from activity")
    List<Activity> getAll();

    @Select("select * from activity where id = #{id}")
    Activity getById(int id);

    @Select("SELECT * from activity ORDER BY id desc limit  #{top}")
    List<Activity> getNew(int top);

    @Select("SELECT * FROM activity order by id ASC LIMIT #{lines} OFFSET #{start}")
    List<Activity> getPage(int lines,int start);

    @Insert("INSERT INTO activity VALUES(null,#{activity_title},#{activity_name},#{activity_content},#{activity_address},#{username},#{time},#{img_src})")
    int addActivity(Activity activity);

    @Update("UPDATE activity SET activity_title=#{activity_title},activity_name=#{activity_name},activity_content=#{activity_content}," +
            "activity_address=#{activity_address},username=#{username},time=#{time},img_src=#{img_src} WHERE id =#{id}")
    int updateActivity(Activity activity);

    @Delete("DELETE FROM activity WHERE id =#{id}")
    int deleteActivityById(int id);
}
