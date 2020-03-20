package com.dxg.msns.user.service.impl;

import com.dxg.msns.user.mapper.FollowMapper;
import com.dxg.msns.user.pojo.Follow;
import com.dxg.msns.user.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowMapper followMapper;
    @Override
    public Integer followCountByUid(String uid) {
        Example example = new Example(Follow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicId",uid);
        Integer counts = followMapper.selectCountByExample(example);
        return counts;
    }
}
