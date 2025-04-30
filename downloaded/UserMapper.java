package com.dowell.dal.mapper;

import com.dowell.dal.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author nanbo
 * @description
 * @create 2018-09-28
 **/
@Component
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 新增用户信息
     * @param userEntity
     * @return
     */
    @Insert("INSERT INTO t_user (USERNAME, PASSWORD, MOBILE, STATUS, CRATE_TIME) VALUES (#{username}, #{password}, #{mobile}, #{status}, #{crateTime})")
    int save(UserEntity userEntity);

    /**
     * 修改
     * @param userEntity
     * @return
     */
    @Update("UPDATE t_user SET username=#{username}, MOBILE=#{mobile},MODIFY_TIME=#{modifyTime}")
    int update(UserEntity userEntity);

    /**
     * 删除
     * @param userId
     * @return
     */
    @Delete("DELETE t_user WHERE user_id=#{userId}")
    int deleteByUserId(Long userId);

    /**
     * 根据主键查询
     * @param userId
     * @return
     */
    @Results(id = "userResult",value = {
            @Result(property = "userId", column = "user_id", id = true),
            @Result(property = "username", column = "username", javaType = String.class),
            @Result(property = "mobile", column = "mobile", javaType = String.class),
            @Result(property = "email", column = "email", javaType = String.class),
            @Result(property = "status", column = "status", javaType = String.class),
            @Result(property = "crateTime", column = "crate_time", javaType = Date.class),
            @Result(property = "modifyTime", column = "modify_time", javaType = Date.class),
            @Result(property = "lastLoginTime", column = "last_login_time", javaType = Date.class)
    })
    @Select("SELECT * FROM t_user WHERE user_id=#{userId}")
    UserEntity getObjectById(Long userId);

    Integer sum1(@Param("mobile") String mobile);

}
