package com.dxg.msns.music.api;

import com.dxg.msns.music.pojo.MusicType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("type")
public interface MusicTypeApi {
    @RequestMapping("queryById/{id}")
    public MusicType queryById(@PathVariable("id")Integer id);
}
