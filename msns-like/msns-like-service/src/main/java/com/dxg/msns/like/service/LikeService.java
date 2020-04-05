package com.dxg.msns.like.service;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.like.pojo.Like;

import java.util.List;

public interface LikeService {
    /**
     * 查询动态点赞数
     * @param dynamicId
     * @return
     */
    Integer queryCountsByDynamicId(Integer dynamicId);

    /**
     * 添加点赞信息
     * @param like
     */
    void addLike(Like like);

    /**
     * 根据动态id和点赞者id查询该用户是否点赞
     * @param dynamicIds
     * @param likerId
     * @return
     */
    List queryIsLike(Integer[] dynamicIds, String likerId);

    /**
     * 删除点赞信息
     * @param like
     */
    void deleteLike(Like like);

    /**
     * 分页查询
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param like
     * @return
     */
    PageResult<Like> queryByPage( Integer page, Integer rows, String[] sortBy, Boolean[] desc, Like like);
}
