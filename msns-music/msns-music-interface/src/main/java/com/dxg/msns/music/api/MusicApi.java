package com.dxg.msns.music.api;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.music.pojo.Music;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface MusicApi {
    /**
     * 根据id查找音乐
     * @param id
     * @return
     */
    @RequestMapping("queryById/{id}")
    public Music queryMusicById(@PathVariable("id") Integer id);

    /**
     * 根据音乐id查找音乐
     * @param musicId
     * @return
     */
    @RequestMapping("queryByMusicId/{id}")
    public Music queryMusicByMusicId(@PathVariable("id") String musicId);

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
    public PageResult<Music> queryMusicsByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy[],
            @RequestParam(value = "desc",required = false)Boolean desc[],
            @RequestParam(value = "isAll",required = false)Boolean isAll
    );
}
