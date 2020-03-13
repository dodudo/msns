package com.dxg.msns.like.service;

public interface LikeService {
    /**
     * 查询动态点赞数
     * @param dynamicId
     * @return
     */
    Integer queryCountsByDynamicId(String dynamicId);
}
