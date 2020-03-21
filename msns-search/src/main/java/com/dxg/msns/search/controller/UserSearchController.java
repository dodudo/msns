package com.dxg.msns.search.controller;

import com.dxg.msns.search.service.UserSearchService;
import com.dxg.msns.user.pojo.UserCountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserSearchController {
    @Autowired
    private UserSearchService userSearchService;

    /**
     * 根据用户id查询用户的一些统计信息
     * @param uid
     * @return
     */
    @RequestMapping("userCountInfo/{uid}")
    public ResponseEntity<UserCountInfo> userCountInfo(@PathVariable("uid")String uid){
        UserCountInfo userCountInfo = userSearchService.userCountInfo(uid);
        if (userCountInfo == null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userCountInfo);
    }
}
