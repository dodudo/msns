package com.dxg.msns.favor.service;

import com.dxg.msns.favor.pojo.MusicFavor;

public interface MusicFavorService {

    /**
     * 查询用户搜藏音乐的总数
     * @param uid
     * @return
     */
    Integer queryCountByUid(String uid);

    /**
     *根据用户id查询该用户收藏音乐的id
     * @param uid
     * @return
     */
    Integer[] queryByUid(String uid);

    /**
     * 删除收藏
     * @param musicFavor
     */
    void deleteFavor(MusicFavor musicFavor);

    /**
     * 添加收藏
     * @param musicFavor
     */
    void addFavor(MusicFavor musicFavor);
}
