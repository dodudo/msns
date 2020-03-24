package com.dxg.msns.user.controller;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.common.util.CodecUtils;
import com.dxg.msns.user.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.dxg.msns.user.pojo.User;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 分页查询所有用户
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param isAll
     * @return
     */
    @RequestMapping("page")
    public ResponseEntity<PageResult<User>> queryUsersByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy[],
            @RequestParam(value = "desc",required = false)Boolean desc[],
            @RequestParam(value = "isAll",required = false)Boolean isAll,
            @RequestParam(value = "uids",required = false)String[] uids
    ){
        if (page<0 ||rows<0){
            return ResponseEntity.badRequest().build();
        }
        PageResult<User> result = this.userService.queryUsersByPage(key,page,rows,sortBy,desc,isAll,uids);

        return ResponseEntity.ok(result);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user){
        //在插入前查询是否存在该用户名
        boolean hadUser = this.userService.findUserByName(user);
        if (!hadUser){
            return new ResponseEntity("已存在该用户！", HttpStatus.BAD_REQUEST);
        }else {
            this.userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

    }

    /**
     * 修改个人信息
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(@RequestBody User user){
        this.userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * 删除个人信息
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@RequestBody User user){
        this.userService.deleteUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 查询用户名和邮箱手机是否可用（type:1验证用户名，2验证手机号，3验证邮箱）
     * @param data
     * @param type
     * @return
     */
    @GetMapping("check/{data}/{type}")
    public ResponseEntity<Boolean> checkUserData(@PathVariable("data") String data,@PathVariable("type") Integer type){
        Boolean check = this.userService.checkUserData(data,type);
        if (check == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(check);
    }

    /**
     * 根据id查找用户头像和昵称
     * @param uid
     * @return
     */
    @RequestMapping("queryNameAvatarById/{id}")
    public ResponseEntity<User> queryNameAvatarById(@PathVariable("id") String uid){
        User user = this.userService.queryNameAvatarById(uid);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        System.out.println("user:::"+user);
        return ResponseEntity.ok(user);
    }

    /**
     * 发送验证码
     * @param email
     * @return
     */
    @PostMapping("code")
    public ResponseEntity<Void> sendVerifyCode(@RequestBody String email){
        if (StringUtils.isEmpty(email)){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String decode = URLDecoder.decode(email);
        email = decode.substring(0, decode.indexOf("="));
//        System.out.println(email);
        Boolean boo = this.userService.sendVerifyCode(email);
        if (!boo){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid @RequestBody User user, @RequestParam("code") String code){
//        System.out.println("用户："+user);
//        System.out.println("code:::"+code);
        Map<String,Object> map = this.userService.register(user,code);
        Boolean boo = (Boolean) map.get("boo");
        if (!boo){
            return new ResponseEntity(map.get("err"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 登录，根据用户名密码查询用户
     * @return
     */
    @GetMapping("login")
    public ResponseEntity<User> login(@RequestParam("uname")String uname,@RequestParam("upassword")String upassword){
        User user = this.userService.queryUser(uname,upassword);
        System.out.println(user);
        if (user == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @RequestMapping("queryById/{id}")
    public ResponseEntity<User> queryById(@PathVariable("id")Integer id){
        User user = this.userService.queryById(id);
        System.out.println(user);
        if (user == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * 根据id修改密码
     * @param id
     * @return
     */
    @GetMapping("changePwdById")
    public ResponseEntity<Void> changePwdById(@RequestParam("id")Integer id,@RequestParam("upassword") String upassword,@RequestParam("newPassword") String newPassword){
        User user = this.userService.queryById(id);
        if (user.getUpassword().equals(CodecUtils.md5Hex(upassword,user.getSalt()))){
            this.userService.changePwdById(id,newPassword);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 根据id修改头像
     * @param id
     * @return
     */
    @GetMapping("changeAvatar")
    public ResponseEntity<Void> changeAvatar(@RequestParam("id")Integer id,@RequestParam("avatarUrl") String avatarUrl){
        this.userService.changeAvatar(id,avatarUrl);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
