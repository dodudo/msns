package com.dxg.msns.like.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface LikeApi {
    /**
     * 查询动态的点赞数
     *
     */
    @GetMapping("queryCountsByDynamicId/{id}")
    public Integer queryCountsByDynamicId(@PathVariable("id") Integer dynamicId);
}
