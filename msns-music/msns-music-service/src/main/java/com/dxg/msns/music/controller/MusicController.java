package com.dxg.msns.music.controller;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.music.pojo.TypeStatistics;
import com.dxg.msns.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.management.MemoryUsage;
import java.util.List;

@RestController
public class MusicController {
    @Autowired
    private MusicService musicService;

    /**
     * 查询所有音乐结果，分页
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param isAll
     * @return
     */
    @RequestMapping("page")
    public ResponseEntity<PageResult<Music>> queryMusicsByPage(
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
        PageResult<Music> result = this.musicService.queryMusicsByPage(key,page,rows,sortBy,desc,isAll);

        return ResponseEntity.ok(result);
    }

    /**
     * 新增音乐
     * @param music
     * @return
     */
    @PostMapping
    public ResponseEntity<String> saveMusic(@RequestBody Music music){
        //在插入前查询是否存在该音乐
        boolean hadMusic = this.musicService.findMusicByInfo(music);
        if (!hadMusic){
            return new ResponseEntity("已存在该音乐！",HttpStatus.BAD_REQUEST);
        }else {
            this.musicService.saveMusic(music);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    /**
     * 获取分类统计信息
     * @return
     */
    @RequestMapping("statistics")
    public ResponseEntity<List<TypeStatistics>> queryTypeStatistics(){
        List<TypeStatistics> typeStatistics = this.musicService.queryTypeStatistics();
        return ResponseEntity.ok(typeStatistics);
    }

    /**
     * 修改音乐信息
     * @param music
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateMusic(@RequestBody Music music){
        this.musicService.updateMusic(music);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * 删除音乐信息
     * @param music
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMusic(@RequestBody Music music){
        this.musicService.deleteMusic(music);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据id查找音乐
     * @param musicId
     * @return
     */
    @RequestMapping("queryById/{id}")
    public ResponseEntity<Music> queryMusicById(@PathVariable("id") String musicId){
        Music music=this.musicService.queryMusicById(musicId);
        return ResponseEntity.ok(music);
    }
}
