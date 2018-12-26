package com.example.club.mapper;

import com.example.club.domain.Enter;
import org.apache.ibatis.annotations.Insert;

public interface EnterMapper {

    @Insert("INSERT into enter values (null,#{activityId},#{studentId})")
    int enterActivty(Enter enter);
}
