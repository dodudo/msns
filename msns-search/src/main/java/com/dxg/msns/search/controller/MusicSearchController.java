package com.dxg.msns.search.controller;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.search.pojo.Dynamics;
import com.dxg.msns.search.pojo.Musics;
import com.dxg.msns.search.pojo.SearchRequst;
import com.dxg.msns.search.service.DynamicSearchService;
import com.dxg.msns.search.service.MusicSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("music")
public class MusicSearchController {
    @Autowired
    private MusicSearchService musicSearchService;

    @PostMapping("page")
    public ResponseEntity<PageResult<Musics>> searchMusic(@RequestBody SearchRequst request){
        System.out.println("request:::::"+request);
        PageResult<Musics> result = this.musicSearchService.searchMusic(request);
        if (result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

}
