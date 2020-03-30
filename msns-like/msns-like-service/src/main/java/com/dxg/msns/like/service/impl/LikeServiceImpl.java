package com.dxg.msns.like.service.impl;

import com.dxg.msns.like.mapper.LikeMapper;
import com.dxg.msns.like.pojo.Like;
import com.dxg.msns.like.service.LikeService;
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

    private void sendUpdateDynamicMsg(Integer id,String type){
        //向队列发送消息
        try {
            this.amqpTemplate.convertAndSend("dynamic."+type, id.toString());
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
        criteria.andEqualTo("dynamicId",dynamicId);
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
        like.setLikeDate(new Date());
        likeMapper.insertSelective(like);
//        System.out.println(like.getDynamicId());
        this.sendUpdateDynamicMsg(like.getDynamicId(),"update");
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
        criteria.andEqualTo("likerId",likerId).andIn("dynamicId", Arrays.asList(dynamicIds));
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
        this.sendUpdateDynamicMsg(like.getDynamicId(),"update");
    }
}
