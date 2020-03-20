package com.dxg.msns.comment.service.impl;

import com.dxg.msns.comment.mapper.CommentMapper;
import com.dxg.msns.comment.pojo.Comment;
import com.dxg.msns.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 根据动态id查询评论
     *
     * @param dynamicId
     * @return
     */
    @Override
    public List<Comment> queryByDynamicId(String dynamicId) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicId",dynamicId);
        List<Comment> comments = commentMapper.selectByExample(example);
        return comments;
    }

    /**
     * 根据动态id查询评论数
     *
     * @param dynamicId
     * @return
     */
    @Override
    public Integer queryCountsByDynamicId(String dynamicId) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicId",dynamicId);
        Integer counts = commentMapper.selectCountByExample(example);
        return counts;
    }

    /**
     * 根据动态id查询最新发布评论
     *
     * @param dynamicId
     * @return
     */
    @Override
    public Date queryRecentCreateDateByDynamicId(String dynamicId) {
        Date date = commentMapper.queryRecentCreateDateByDynamicId(dynamicId);
        return date;
    }

    @Override
    public List<Comment> queryByUid(String uid) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("replyId",uid);
        List<Comment> comments = commentMapper.selectByExample(example);
        return comments;
    }
}
