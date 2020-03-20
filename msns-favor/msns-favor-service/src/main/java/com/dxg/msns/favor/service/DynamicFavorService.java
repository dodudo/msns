package com.dxg.msns.favor.service;

import org.springframework.http.ResponseEntity;

public interface DynamicFavorService {
    /**
     * 查询动态的收藏数
     * @param dynamicId
     * @return
     */
    Integer queryByDynamicId(String dynamicId);

    /**
     * 查询用户收藏动态的数量
     * @param uid
     * @return
     */
    Integer queryByUid(String uid);
}
