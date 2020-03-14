package com.dxg.msns.user.api;

import com.dxg.msns.user.pojo.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface UserApi {
    /**
     * 根据id查找用户头像和昵称
     * @param uid
     * @return
     */
    @RequestMapping("queryNameAvatarById/{id}")
    public User queryNameAvatarById(@PathVariable("id") String uid);
}
