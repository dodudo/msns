package com.dxg.msns.favor.controller;

import com.dxg.msns.favor.service.MusicFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (MusicFavor)表控制层
 *
 * @author makejava
 * @since 2020-03-13 20:46:05
 */
@RestController
@RequestMapping("music")
public class MusicFavorController {
    /**
     * 服务对象
     */
    @Autowired
    private MusicFavorService musicFavorService;


    /**
     * 查询用户音乐的收藏数
     *
     */
    @GetMapping("queryCountByUid/{id}")
    public ResponseEntity<Integer> queryCountByUid(@PathVariable("id") String uid) {
        Integer counts = this.musicFavorService.queryCountByUid(uid);
        if (counts == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(counts);
    }

}