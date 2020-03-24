package com.dxg.msns.user.mapper;

import com.dxg.msns.user.pojo.User;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    @Select("select uid,uname,avatar_url from user where uid = #{uid}")
    @Results(id = "userMap",
            value = {
                    @Result(id = true,column = "uid",property = "uid"),
                    @Result(column = "uname",property = "uname"),
                    @Result(column = "avatar_url",property = "avatarUrl"),
            })
    User queryNameAvatarById(@Param("uid") String uid);

    /**
     * 修改密码
     * @param id
     * @param newPwd
     * @param salt
     */
    @Update("update user set upassword = #{newPwd} , salt = #{salt} where id=#{id}")
    void changePwdById(@Param("id") Integer id, @Param("newPwd") String newPwd,@Param("salt") String salt);

    /**
     * 修改头像
     * @param id
     * @param avatarUrl
     */
    @Update("update user set avatar_url = #{avatarUrl} where id = #{id}")
    @ResultMap("userMap")
    void changeAvatar(@Param("id")Integer id,@Param("avatarUrl") String avatarUrl);
}
