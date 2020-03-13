package com.dxg.msns.like.controller;

import com.dxg.msns.like.pojo.Like;
import com.dxg.msns.like.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Like)表控制层
 *
 * @author makejava
 * @since 2020-03-13 21:04:50
 */
@RestController
public class LikeController {
    /**
     * 服务对象
     */
    @Resource
    private LikeService likeService;

    /**
     * 查询动态的点赞数
     *
     */
    @GetMapping("queryCountsByDynamicId/{id}")
    public ResponseEntity<Integer> queryCountsByDynamicId(@PathVariable("id") String dynamicId) {
        Integer counts = this.likeService.queryCountsByDynamicId(dynamicId);
        if (counts == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(counts);
    }

}