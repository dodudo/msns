package com.dxg.msns.favor.service;

public interface MusicFavorService {

    /**
     * 查询用户搜藏音乐的总数
     * @param uid
     * @return
     */
    Integer queryCountByUid(String uid);
}
