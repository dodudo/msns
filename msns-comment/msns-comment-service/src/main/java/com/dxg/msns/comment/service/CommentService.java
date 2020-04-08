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

    /**
     * 添加评论
     * @param comment
     */
    Comment add(Comment comment);

    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    Comment queryById(Integer id);


    /**
     * 根据id修改状态
     * @param id
     */
    void updateDeleteStateById(Long id,Integer dynamicId);

    /**
     * 根据id批量修改状态，已读
     * @param ids
     */
    void updateStateByIds(Long[] ids,String status,String respondentId);

    /**
     * 根据回复者id查询目前用户未读消息次数
     * @param respondentId
     * @return
     */
    Integer queryCountsByRespondentId(String respondentId);
}
