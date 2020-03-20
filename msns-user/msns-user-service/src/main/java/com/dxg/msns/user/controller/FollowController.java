package com.dxg.msns.user.controller;

import com.dxg.msns.user.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("follow")
public class FollowController {
    @Autowired
    private FollowService followService;
    /**
     * 根据用户id查询该用户关注数
     * @param uid
     * @return
     */
    @RequestMapping("followCountByUid/{uid}")
    public ResponseEntity<Integer> followCountByUid(@PathVariable("uid")String uid){
        Integer counts = this.followService.followCountByUid(uid);
        if (counts == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(counts);
    }
}
