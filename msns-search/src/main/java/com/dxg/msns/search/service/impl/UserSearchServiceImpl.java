package com.dxg.msns.search.service.impl;

import com.dxg.msns.search.client.*;
import com.dxg.msns.search.reponsitory.DynamicsRepository;
import com.dxg.msns.search.service.UserSearchService;
import com.dxg.msns.user.pojo.UserCountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSearchServiceImpl implements UserSearchService {
    @Autowired
    private DynamicsClient dynamicsClient;

    @Autowired
    private DynamicTypeClient dynamicTypeClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private MusicClient musicClient;

    @Autowired
    private CommentClient commentClient;

    @Autowired
    private DynamicFavorClient dynamicFavorClient;
    @Autowired
    private MusicFavorClient musicFavorClient;
    @Autowired
    private FollowClient followClient;

    /**
     * 根据用户id查询用户的一些统计信息
     *
     * @param uid
     * @return
     */
    @Override
    public UserCountInfo userCountInfo(String uid) {
        UserCountInfo userCountInfo = new UserCountInfo();
        //用户动态收藏数
        userCountInfo.setCollectDynamicCount(dynamicFavorClient.queryByUid(uid));
        //用户音乐收藏数
        userCountInfo.setCollectMusicCount(musicFavorClient.queryCountByUid(uid));
        //用户动态数
        userCountInfo.setDynamicCount(dynamicsClient.queryCountByUid(uid));
        //用户关注数
        userCountInfo.setFanCount(followClient.fanCountByUid(uid));
        //用户粉丝数
        userCountInfo.setFavorCount(followClient.followCountByUid(uid));
        return userCountInfo;
    }


}
