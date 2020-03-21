package com.dxg.msns.search.service;

import com.dxg.msns.user.pojo.UserCountInfo;

public interface UserSearchService {
    /**
     * 根据用户id查询用户的一些统计信息
     * @param uid
     * @return
     */
    UserCountInfo userCountInfo(String uid);
}
