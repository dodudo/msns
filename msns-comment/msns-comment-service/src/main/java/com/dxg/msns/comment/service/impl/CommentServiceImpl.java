package com.dxg.msns.comment.service.impl;

import com.dxg.msns.comment.mapper.CommentMapper;
import com.dxg.msns.comment.pojo.Comment;
import com.dxg.msns.comment.service.CommentService;
import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.common.util.UnderlineHump;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    private void sendMsg(Integer id, String type) {
        //向队列发送消息
        try {
            this.amqpTemplate.convertAndSend("dynamic." + type, id.toString());
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }
    private void sendSmsMsg(String id, String type) {
        //向某用户发送消息
        try {
            this.amqpTemplate.convertAndSend("msns.sms.exchange","sms." + type, id.toString());
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据动态id查询评论
     *
     * @param dynamicId
     * @return
     */
    @Override
    public List<Comment> queryByDynamicId(Integer dynamicId) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicId", dynamicId);
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
    public Integer queryCountsByDynamicId(Integer dynamicId) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicId", dynamicId);
        criteria.andEqualTo("status", "1");
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
    public Date queryRecentCreateDateByDynamicId(Integer dynamicId) {
        Date date = commentMapper.queryRecentCreateDateByDynamicId(dynamicId);
        return date;
    }

    @Override
    public List<Comment> queryByUid(String uid) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("replyId", uid);
        List<Comment> comments = commentMapper.selectByExample(example);
        return comments;
    }

    /**
     * 根据分页查询
     *
     * @param key     关键字
     * @param page    查询页
     * @param rows    查询行数
     * @param sortBy  排序
     * @param desc    是否倒序
     * @param isAll   是否查询有效评论
     * @param comment 评论
     * @return
     */
    @Override
    public PageResult<Comment> queryByPage(String key, Integer page, Integer rows, String[] sortBy, Boolean[] desc, Boolean isAll, Comment comment) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        if (isAll) {
            criteria.andNotEqualTo("status", "-1");
        } else {
            criteria.andEqualTo("status", "-1");
        }
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("CommentContent", "%" + key + "%");
        }
        //查询动态下面的评论
        if (comment.getDynamicId() != null) {
            criteria.andEqualTo("dynamicId", comment.getDynamicId());
        }

        if (comment.getPid() != null) {
            //根据父id查询
            criteria.andEqualTo("pid", comment.getPid());
        } else {
            System.out.println(comment.getReplyId() == null && comment.getRespondentId() == null);
            if (comment.getReplyId() == null && comment.getRespondentId() == null) {

                criteria.andIsNull("pid");
            }

        }


        //根据评论者id
        if (comment.getReplyId() != null) {
            criteria.andEqualTo("replyId", comment.getPid());
        }
        //根据回复者id
        if (comment.getRespondentId() != null) {
            criteria.andEqualTo("respondentId", comment.getRespondentId());
        }
        //添加分页条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (ArrayUtils.isNotEmpty(sortBy)) {
            example.setOrderByClause(UnderlineHump.HumpToUnderline(sortBy[0]) + " " + (desc[0] ? "desc" : "asc"));
        }
        List<Comment> comments = this.commentMapper.selectByExample(example);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);

        return new PageResult<>(commentPageInfo.getTotal(), commentPageInfo.getList());
    }

    /**
     * 添加评论
     *
     * @param comment
     */
    @Override
    @Transactional
    public Comment add(Comment comment) {
        comment.setCommentDate(new Date());
        comment.setStatus("1");
        this.commentMapper.insertSelective(comment);
        sendMsg(comment.getDynamicId(), "update");
        sendSmsMsg(comment.getRespondentId(),comment.getRespondentId());
        return comment;
    }

    /**
     * 根据id查询评论
     *
     * @param id
     * @return
     */
    @Override
    public Comment queryById(Integer id) {
        Comment comment = this.commentMapper.selectByPrimaryKey(id);
        return comment;
    }

    /**
     * 根据id修改状态
     *
     * @param id
     */
    @Override
    public void updateDeleteStateById(Long id,Integer dynamicId) {
        this.commentMapper.updateDeleteStateById(id);
        sendMsg(dynamicId, "update");
    }

    /**
     * 根据id批量修改状态，已读
     *
     * @param ids
     */
    @Override
    public void updateStateByIds(Long[] ids,String status,String respondentId) {
        this.commentMapper.updateStateByIds(ids,status,respondentId);
    }

    /**
     * 根据回复者id查询目前用户未读消息次数
     *
     * @param respondentId
     * @return
     */
    @Override
    public Integer queryCountsByRespondentId(String respondentId) {
        Example example = new Example(Comment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("respondentId", respondentId);
        criteria.andEqualTo("status", "1");
        Integer counts = commentMapper.selectCountByExample(example);
        return counts;
    }
}
