package com.dxg.msns.like.service.impl;

import com.dxg.msns.like.mapper.LikeMapper;
import com.dxg.msns.like.pojo.Like;
import com.dxg.msns.like.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeMapper likeMapper;
    /**
     * 查询动态点赞数
     *
     * @param dynamicId
     * @return
     */
    @Override
    public Integer queryCountsByDynamicId(String dynamicId) {
        Example example = new Example(Like.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicId",dynamicId);
        Integer counts = likeMapper.selectCountByExample(example);
        return counts;
    }
}
