package com.dxg.msns.favor.controller;

import com.dxg.msns.favor.pojo.DynamicFavor;
import com.dxg.msns.favor.service.DynamicFavorService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DynamicFavor)表控制层
 *
 * @author makejava
 * @since 2020-03-13 20:45:34
 */
@RestController
@RequestMapping("dynamic")
public class DynamicFavorController {
    /**
     * 服务对象
     */
    @Autowired
    private DynamicFavorService dynamicFavorService;

    /**
     * 查询动态的收藏数
     *
     */
    @GetMapping("queryByDynamicId/{id}")
    public ResponseEntity<Integer> queryByDynamicId(@PathVariable("id") Integer dynamicId) {
        Integer counts = this.dynamicFavorService.queryByDynamicId(dynamicId);
        if (counts == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(counts);
    }

    /**
     * 查询用户收藏动态的收藏数
     *
     */
    @GetMapping("queryByUid/{uid}")
    public ResponseEntity<Integer> queryByUid(@PathVariable("uid") String uid) {
        Integer counts = this.dynamicFavorService.queryByUid(uid);
        if (counts == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(counts);
    }

    /**
     * 查询用户收藏动态的收藏数
     *
     */
    @GetMapping("queryFavorByUid/{uid}")
    public ResponseEntity<Integer[]> queryFavorByUid(@PathVariable("uid") String uid) {
        Integer[] ids = this.dynamicFavorService.queryFavorByUid(uid);
        if (ids == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ids);
    }
}