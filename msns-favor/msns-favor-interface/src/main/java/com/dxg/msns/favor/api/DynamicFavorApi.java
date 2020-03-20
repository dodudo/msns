package com.dxg.msns.favor.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("dynamic")
public interface DynamicFavorApi {
    /**
     * 查询动态的收藏数
     *
     */
    @GetMapping("queryByDynamicId/{id}")
    public Integer queryByDynamicId(@PathVariable("id") String dynamicId);

    /**
     * 查询用户收藏动态的收藏数
     *
     */
    @GetMapping("queryByUid/{uid}")
    public Integer queryByUid(@PathVariable("uid") String uid);
}
