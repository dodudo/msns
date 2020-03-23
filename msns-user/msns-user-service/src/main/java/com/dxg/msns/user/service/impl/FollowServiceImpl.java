package com.dxg.msns.user.service.impl;

import com.dxg.msns.user.mapper.FollowMapper;
import com.dxg.msns.user.pojo.Follow;
import com.dxg.msns.user.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    /**
     * 根据用户id查询该用户和粉丝是否互相关注
     *
     * @param uids
     * @return
     */
    @Override
    public String[] queryIsMutual(String[] uids,String currentUid) {
        List<String> mutualUidsList = new ArrayList<>();
        Follow follow = new Follow();
        //查询是否被当前用户关注
        for (int i=0;i<uids.length;i++){
            follow.setFollowersId(currentUid);
            follow.setBefollowersId(uids[i]);
            int count = this.followMapper.selectCount(follow);
            if (count >= 1){
                mutualUidsList.add(uids[i]);
            }
        }
        System.out.println("mutualUidsList:::"+mutualUidsList);
        String[] mutualUids = mutualUidsList.toArray(new String[mutualUidsList.size()]);
        return mutualUids;
    }

    /**
     * 删除关注信息
     *
     * @param followerId
     * @param beFollowerId
     * @return
     */
    @Override
    public void deleteFollow(String followerId, String beFollowerId) {
        Example example = new Example(Follow.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("followersId",followerId).andEqualTo("befollowersId",beFollowerId);
        this.followMapper.deleteByExample(example);
    }

    /**
     * 新增关注
     *
     * @param follow
     */
    @Override
    public void addFollow(Follow follow) {
        Date date = new Date();
        follow.setFollowerDate(date);
        this.followMapper.insertSelective(follow);
    }
}
