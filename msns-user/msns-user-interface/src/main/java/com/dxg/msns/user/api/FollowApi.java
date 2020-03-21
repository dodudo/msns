package com.dxg.msns.user.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("follow")
public interface FollowApi {
    /**
     * 根据用户id查询该用户关注别人的数
     *
     * @param uid
     * @return
     */
    @RequestMapping("followCountByUid/{uid}")
    public Integer followCountByUid(@PathVariable("uid") String uid);

    /**
     * 根据用户id查询该用户粉丝数
     *
     * @param uid
     * @return
     */
    @RequestMapping("fanCountByUid/{uid}")
    public Integer fanCountByUid(@PathVariable("uid") String uid);

    /**
     * 根据用户id查询该用户关注用户的uid
     *
     * @param uid
     * @return
     */
    @RequestMapping("queryFollowerIdsByUid/{uid}")
    public String[] queryFollowerIdByUid(@PathVariable("uid") String uid);

    /**
     * 根据用户id查询该用户粉丝的uid
     *
     * @param uid
     * @return
     */
    @RequestMapping("queryFanIdsByUid/{uid}")
    public String[] queryFanIdByUid(@PathVariable("uid") String uid);
}
