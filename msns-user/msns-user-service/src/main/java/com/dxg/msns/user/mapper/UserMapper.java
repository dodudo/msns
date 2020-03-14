package com.dxg.msns.user.mapper;

import com.dxg.msns.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    @Select("select uid,uname,avatar_url from user where uid = #{uid}")
    User queryNameAvatarById(@Param("uid") String uid);
}
