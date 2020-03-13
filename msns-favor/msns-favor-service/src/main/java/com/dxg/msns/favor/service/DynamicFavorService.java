package com.dxg.msns.favor.service;

import org.springframework.http.ResponseEntity;

public interface DynamicFavorService {
    /**
     * 查询动态的收藏数
     * @param dynamicId
     * @return
     */
    Integer queryByDynamicId(String dynamicId);
}
