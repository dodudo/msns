package com.dxg.msns.like.service.impl;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.common.util.UnderlineHump;
import com.dxg.msns.like.mapper.LikeMapper;
import com.dxg.msns.like.pojo.Like;
import com.dxg.msns.like.service.LikeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {


    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    private void sendUpdateDynamicMsg(Integer id, String type) {
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
     * 查询动态点赞数
     *
     * @param dynamicId
     * @return
     */
    @Override
    public Integer queryCountsByDynamicId(Integer dynamicId) {
        Example example = new Example(Like.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicId", dynamicId);
        Integer counts = likeMapper.selectCountByExample(example);
        return counts;
    }

    /**
     * 添加点赞信息
     *
     * @param like
     */
    @Override
    public void addLike(Like like) {
        like.setStatus("1");
        like.setLikeDate(new Date());
        likeMapper.insertSelective(like);
//        System.out.println(like.getDynamicId());
        this.sendUpdateDynamicMsg(like.getDynamicId(), "update");
        this.sendSmsMsg(like.getDynamicAuthorid(),like.getDynamicAuthorid());
    }

    /**
     * 根据动态id和点赞者id查询该用户是否点赞
     *
     * @param dynamicIds
     * @param likerId
     * @return
     */
    @Override
    public List queryIsLike(Integer[] dynamicIds, String likerId) {
        Example example = new Example(Like.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("likerId", likerId).andIn("dynamicId", Arrays.asList(dynamicIds));
        List<Like> likeList = likeMapper.selectByExample(example);
        return likeList;
    }

    /**
     * 删除点赞信息
     *
     * @param like
     */
    @Override
    public void deleteLike(Like like) {

        this.likeMapper.delete(like);
//        System.out.println(like.getDynamicId());
        this.sendUpdateDynamicMsg(like.getDynamicId(), "update");
        this.sendSmsMsg(like.getDynamicAuthorid(),like.getDynamicAuthorid());
    }

    /**
     * 分页查询
     *
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param like
     * @return
     */
    @Override
    public PageResult<Like> queryByPage(Integer page, Integer rows, String[] sortBy, Boolean[] desc, Like like) {
        Example example = new Example(Like.class);
        Example.Criteria criteria = example.createCriteria();

        //根据评论者id
        criteria.andEqualTo("dynamicAuthorid", like.getDynamicAuthorid());

        //添加分页条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (ArrayUtils.isNotEmpty(sortBy)) {
            example.setOrderByClause(UnderlineHump.HumpToUnderline(sortBy[0]) + " " + (desc[0] ? "desc" : "asc"));
        }
        List<Like> likes = this.likeMapper.selectByExample(example);
        PageInfo<Like> likePageInfo = new PageInfo<>(likes);

        return new PageResult<>(likePageInfo.getTotal(), likePageInfo.getList());
    }

    /**
     * 根据id修改状态
     *
     * @param ids
     * @param status
     */
    @Override
    public void updateStateByIds(Integer[] ids, String status,String dynamicAuthorid) {
        this.likeMapper.updateStateByIds(ids,status,dynamicAuthorid);
    }

    /**
     * 查询未读消息次数
     *
     * @param dynamicAuthorid
     * @return
     */
    @Override
    public Integer queryCountsByDynamicAuthorid(String dynamicAuthorid) {
        Example example = new Example(Like.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicAuthorid", dynamicAuthorid);
        criteria.andEqualTo("status", "1");
        Integer counts = likeMapper.selectCountByExample(example);
        return counts;
    }
}
