package com.dxg.msns.user.controller;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.dxg.msns.user.pojo.User;

import java.util.List;

@Controller
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
            @RequestParam(value = "isAll",required = false)Boolean isAll
    ){
        if (page<0 ||rows<0){
            return ResponseEntity.badRequest().build();
        }
        PageResult<User> result = this.userService.queryUsersByPage(key,page,rows,sortBy,desc,isAll);

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
     * 查询用户名和邮箱手机是否可用
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
        return ResponseEntity.ok(user);
    }
}
