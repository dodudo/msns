package com.dxg.msns.user.controller;

import com.dxg.msns.user.service.FollowService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    /**
     * 根据用户id查询该用户粉丝数
     * @param uid
     * @return
     */
    @RequestMapping("fanCountByUid/{uid}")
    public ResponseEntity<Integer> fanCountByUid(@PathVariable("uid")String uid){
        Integer counts = this.followService.fanCountByUid(uid);
        if (counts == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(counts);
    }

    /**
     * 根据用户id查询该用户关注用户的uid
     * @param uid
     * @return
     */
    @RequestMapping("queryFollowerIdsByUid/{uid}")
    public ResponseEntity<String[]> queryFollowerIdByUid(@PathVariable("uid")String uid){
        String[] uids  = this.followService.queryFollowerIdByUid(uid);
        if (ArrayUtils.isEmpty(uids)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(uids);
    }
    /**
     * 根据用户id查询该用户粉丝的uid
     * @param uid
     * @return
     */
    @RequestMapping("queryFanIdsByUid/{uid}")
    public ResponseEntity<String[]> queryFanIdByUid(@PathVariable("uid")String uid){
        String[] uids = this.followService.queryFanIdByUid(uid);
        if (ArrayUtils.isEmpty(uids)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(uids);
    }
}
