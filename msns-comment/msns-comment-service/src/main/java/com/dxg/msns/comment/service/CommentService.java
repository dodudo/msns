package com.dxg.msns.comment.service;

import com.dxg.msns.comment.pojo.Comment;

import java.util.Date;
import java.util.List;

public interface CommentService {
    /**
     * 根据动态id查询评论
     * @param dynamicId
     * @return
     */
    List<Comment> queryByDynamicId(String dynamicId);

    /**
     * 根据动态id查询评论数
     * @param dynamicId
     * @return
     */
    Integer queryCountsByDynamicId(String dynamicId);

    /**
     * 根据动态id查询最新发布评论
     * @param dynamicId
     * @return
     */
    Date queryRecentCreateDateByDynamicId(String dynamicId);
}
