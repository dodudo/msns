package com.dxg.msns.search.service;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.search.pojo.SearchRequst;

public interface MusicSearchService {
    /**
     * 查询音乐
     * @param request
     * @return
     */
    public PageResult<Music> searchMusic(SearchRequst request);
}
