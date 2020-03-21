package com.dxg.msns.user.service.impl;

import com.dxg.msns.user.mapper.FollowMapper;
import com.dxg.msns.user.pojo.Follow;
import com.dxg.msns.user.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowMapper followMapper;
    @Override
    public Integer followCountByUid(String uid) {
        Example example = new Example(Follow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("followersId",uid);
        Integer counts = followMapper.selectCountByExample(example);
        return counts;
    }

    /**
     * 根据用户id查询该用户粉丝数
     *
     * @param uid
     * @return
     */
    @Override
    public Integer fanCountByUid(String uid) {
        Example example = new Example(Follow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("befollowersId",uid);
        Integer counts = followMapper.selectCountByExample(example);
        return counts;
    }

    /**
     * 根据用户id查询该用户粉丝的uid
     *
     * @param uid
     * @return
     */
    @Override
    public String[] queryFanIdByUid(String uid) {
        Example example = new Example(Follow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("befollowersId",uid);
        List<Follow> fans = followMapper.selectByExample(example);
        List<String> fansIds = new ArrayList<>();
        for (Follow fan : fans) {
            fansIds.add(fan.getFollowersId());
        }
        String[] fansIdsArr =fansIds.toArray(new String[fans.size()]);
        return fansIdsArr;
    }

    /**
     * 根据用户id查询该用户关注用户的uid
     *
     * @param uid
     * @return
     */
    @Override
    public String[] queryFollowerIdByUid(String uid) {
        Example example = new Example(Follow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("followersId",uid);
        List<Follow> follows = followMapper.selectByExample(example);
        List<String> followsIds = new ArrayList<>();
        for (Follow follow : follows) {
            followsIds.add(follow.getBefollowersId());
        }
        String[] followsIdsArr = followsIds.toArray(new String[follows.size()]);
        return followsIdsArr;
    }
}
