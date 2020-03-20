package com.dxg.msns.search.controller;

import com.dxg.msns.user.pojo.UserCountInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserSearchController {
    @RequestMapping("userCountInfo/{uid}")
    public ResponseEntity<UserCountInfo> userCountInfo(@RequestParam("uid")String uid){
        return null;
    }
}
