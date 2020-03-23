package com.dxg.msns.user.service;

import com.dxg.msns.user.pojo.Follow;

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
    /**
     * 根据用户id查询该用户和粉丝是否互相关注
     * @param uids
     * @return
     */
    String[] queryIsMutual(String[] uids,String currentUid);
    /**
     * 删除关注信息
     * @param followerId
     * @param beFollowerId
     * @return
     */
    void deleteFollow(String followerId, String beFollowerId);

    /**
     * 新增关注
     * @param follow
     */
    void addFollow(Follow follow);
}
