package com.dxg.msns.comment.service;

import com.dxg.msns.comment.pojo.Comment;
import com.dxg.msns.common.pojo.PageResult;

import java.util.Date;
import java.util.List;

public interface CommentService {
    /**
     * 根据动态id查询评论
     * @param dynamicId
     * @return
     */
    List<Comment> queryByDynamicId(Integer dynamicId);

    /**
     * 根据动态id查询评论数
     * @param dynamicId
     * @return
     */
    Integer queryCountsByDynamicId(Integer dynamicId);

    /**
     * 根据动态id查询最新发布评论
     * @param dynamicId
     * @return
     */
    Date queryRecentCreateDateByDynamicId(Integer dynamicId);

    /**
     * 根据用户id查询评论
     * @param uid
     * @return
     */
    List<Comment> queryByUid(String uid);

    /**
     * 根据分页查询
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param isAll
     * @param comment
     * @return
     */
    PageResult<Comment> queryByPage(String key, Integer page, Integer rows, String[] sortBy, Boolean[] desc, Boolean isAll, Comment comment);
}
