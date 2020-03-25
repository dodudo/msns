package com.dxg.msns.music.service;

import com.dxg.msns.music.pojo.MusicType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MusicTypeService {
    List<MusicType> queryAllTypes();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    MusicType queryById(Integer id);
}
