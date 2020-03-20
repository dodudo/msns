package com.dxg.msns.user.service;

public interface FollowService {
    /**
     * 根据用户id查询该用户的关注数
     * @param uid
     * @return
     */
    Integer followCountByUid(String uid);
}
