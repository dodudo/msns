package com.dxg.msns.favor.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("music")
public interface MusicFavorApi {
    /**
     * 查询用户音乐的收藏数
     *
     */
    @GetMapping("queryCountByUid/{id}")
    public Integer queryCountByUid(@PathVariable("id") String uid);

    /**
     * 根据用户id查询该用户收藏音乐的id
     */
    @GetMapping("queryByUid/{id}")
    public Integer[] queryByUid(@PathVariable("id") String uid);

}
