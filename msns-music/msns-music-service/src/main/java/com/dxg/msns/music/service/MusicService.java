package com.dxg.msns.music.service;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.music.pojo.TypeStatistics;

import java.util.List;

public interface MusicService {
    /**
     * 查询所有音乐分类
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param isAll
     * @return
     */
    public PageResult<Music> queryMusicsByPage(String key, Integer page, Integer rows, String[] sortBy, Boolean[] desc, Boolean isAll);

    /**
     * 查询音乐分类统计
     * @return
     */
    List<TypeStatistics> queryTypeStatistics();

    /**
     * 修改音乐
     * @param music
     */
    void updateMusic(Music music);

    /**
     * 添加音乐
     * @param music
     */
    void saveMusic(Music music);
}
