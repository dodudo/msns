package com.dxg.msns.search.controller;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.search.pojo.Dynamics;
import com.dxg.msns.search.pojo.SearchRequst;
import com.dxg.msns.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping("dynamicPage")
    public ResponseEntity<PageResult<Dynamics>> searchDynamic(@RequestBody SearchRequst request){
        PageResult<Dynamics> result = this.searchService.searchDynamics(request);
        if (result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
}
