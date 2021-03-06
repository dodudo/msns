package com.dxg.msns.user.api;

import com.dxg.msns.user.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    /**
     * 根据id查找用户头像和昵称
     * @param uid
     * @return
     */
    @RequestMapping("queryNameAvatarById/{id}")
    public User queryNameAvatarById(@PathVariable("id") String uid);
    /**
     * 登录，根据用户名密码查询用户
     * @return
     */
    @GetMapping("login")
    public User login(@RequestParam("uname")String uname, @RequestParam("upassword")String upassword);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @RequestMapping("queryById/{id}")
    public User queryById(@PathVariable("id")Integer id);

    /**
     * 根据id修改密码
     * @param id
     * @return
     */
    @GetMapping("changePwdById")
    public Void changePwdById(@RequestParam("id")Integer id,
                                              @RequestParam("upassword") String upassword,
                                              @RequestParam("newPassword") String newPassword);
}
