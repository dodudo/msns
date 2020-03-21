package com.dxg.msns.favor.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DynamicFavorService {
    /**
     * 查询动态的收藏数
     * @param dynamicId
     * @return
     */
    Integer queryByDynamicId(Integer dynamicId);

    /**
     * 查询用户收藏动态的数量
     * @param uid
     * @return
     */
    Integer queryByUid(String uid);

    /**
     * 查询用户关注的所有动态id
     * @param uid
     * @return
     */
    Integer[] queryFavorByUid(String uid);
}
