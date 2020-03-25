package com.dxg.msns.search.service;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.search.pojo.Musics;
import com.dxg.msns.search.pojo.SearchRequst;

public interface MusicSearchService {

    /**
     * 构建Musics
     * @param music
     * @return
     */
    public Musics buildMusic(Music music);

    /**
     * 查询音乐
     * @param request
     * @return
     */
    public PageResult<Musics> searchMusic(SearchRequst request);

    /**
     * 新增修改音乐
     * @param id
     */
    void createMusicIndex(Integer id);

    /**
     * 删除音乐索引
     * @param id
     */
    void deleteMusicIndex(Integer id);
}
