package com.dxg.msns.music.api;

import com.dxg.msns.music.pojo.Music;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface MusicApi {
    /**
     * 根据id查找音乐
     * @param musicId
     * @return
     */
    @RequestMapping("queryById/{id}")
    public Music queryMusicById(@PathVariable("id") String musicId);
}
