package com.dxg.msns.favor.controller;

import com.dxg.msns.favor.pojo.MusicFavor;
import com.dxg.msns.favor.service.MusicFavorService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * 根据用户id查询该用户收藏音乐的id
     */
    @GetMapping("queryByUid/{id}")
    public ResponseEntity<Integer[]> queryByUid(@PathVariable("id") String uid) {
        Integer[] ids = this.musicFavorService.queryByUid(uid);
        if (ArrayUtils.isEmpty(ids)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ids);
    }

    /**
     * 删除收藏信息
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFavor(@RequestParam("collectorId")String collectorId,
                                             @RequestParam("musicId")Integer musicId) {
        MusicFavor musicFavor = new MusicFavor();
        musicFavor.setCollectorId(collectorId);
        musicFavor.setMusicId(musicId);
        this.musicFavorService.deleteFavor(musicFavor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 新增关注
     *
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addFavor(@RequestParam("collectorId")String collectorId,
                                            @RequestParam("musicId")Integer musicId) {
        MusicFavor musicFavor = new MusicFavor();
        musicFavor.setCollectorId(collectorId);
        musicFavor.setMusicId(musicId);
        musicFavorService.addFavor(musicFavor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}