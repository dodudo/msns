package com.dxg.msns.search.service.impl;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.search.pojo.SearchRequst;
import com.dxg.msns.search.service.MusicSearchService;
import org.springframework.stereotype.Service;

@Service
public class MusicSearchServiceImpl implements MusicSearchService {
    /**
     * 查询音乐
     *
     * @param request
     * @return
     */
    @Override
    public PageResult<Music> searchMusic(SearchRequst request) {
        return null;
    }
}
