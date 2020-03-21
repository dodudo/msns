package com.dxg.msns.user.service;

public interface FollowService {
    /**
     * 根据用户id查询该用户的关注数
     * @param uid
     * @return
     */
    Integer followCountByUid(String uid);

    /**
     * 根据用户id查询该用户粉丝数
     * @param uid
     * @return
     */
    Integer fanCountByUid(String uid);

    /**
     *根据用户id查询该用户粉丝的uid
     * @param uid
     * @return
     */
    String[] queryFanIdByUid(String uid);

    /**
     * 根据用户id查询该用户关注用户的uid
     * @param uid
     * @return
     */
    String[] queryFollowerIdByUid(String uid);
}
