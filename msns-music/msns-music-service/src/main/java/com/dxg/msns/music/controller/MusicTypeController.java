package com.dxg.msns.music.controller;

import com.dxg.msns.music.pojo.MusicType;
import com.dxg.msns.music.service.MusicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("type")
public class MusicTypeController {
    @Autowired
    private MusicTypeService musicTypeService;

    @RequestMapping("all")
    public ResponseEntity<List<MusicType>> queryAllTypes(){
        List<MusicType> musicTypes = musicTypeService.queryAllTypes();
        return ResponseEntity.ok(musicTypes);
    }
}
