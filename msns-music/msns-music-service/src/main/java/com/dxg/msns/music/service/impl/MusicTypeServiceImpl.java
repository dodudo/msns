package com.dxg.msns.music.service.impl;

import com.dxg.msns.music.mapper.MusicTypeMapper;
import com.dxg.msns.music.pojo.MusicType;
import com.dxg.msns.music.service.MusicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MusicTypeServiceImpl implements MusicTypeService {
    @Autowired
    private MusicTypeMapper musicTypeMapper;
    @Override
    public List<MusicType> queryAllTypes() {
        return musicTypeMapper.selectAll();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public MusicType queryById(Integer id) {
        MusicType musicType = this.musicTypeMapper.selectByPrimaryKey(id);
        return musicType;
    }
}
