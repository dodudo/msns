package com.dxg.msns.search.service.impl;

import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.favor.pojo.DynamicFavor;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.search.client.*;
import com.dxg.msns.search.pojo.Dynamics;
import com.dxg.msns.search.service.SearchService;
import com.dxg.msns.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

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
    private LikeClient likeClient;

    @Override
    public Dynamics buildDynamics(Dynamic dynamic) {

        User user = userClient.queryNameAvatarById(dynamic.getUid());
        String type = dynamicTypeClient.queryNameById(dynamic.getDynamicTypeId());
        Dynamics dynamics = new Dynamics();
        dynamics.setId(dynamic.getId());
        dynamics.setAll(type+dynamic.getDynamicContent()+user.getUname());
        dynamics.setTypeId(dynamic.getDynamicTypeId());
        dynamics.setPublishDate(dynamic.getPublishDate());
        dynamics.setRecentCommentDate(commentClient.queryRecentCreateDateByDynamicId(dynamic.getDynamicId()));
        dynamics.setDynamicContent(dynamic.getDynamicContent());
        dynamics.setViewCount(dynamic.getViewCount());
        dynamics.setLikeCount(likeClient.queryCountsByDynamicId(dynamic.getDynamicId()));
        dynamics.setFavorCount(dynamicFavorClient.queryByDynamicId(dynamic.getDynamicId()));
        dynamics.setCommentCount(commentClient.queryCountsByDynamicId(dynamic.getDynamicId()));
        dynamics.setImgUrls(dynamic.getImgUrls());
        dynamics.setAuthor(user.getUname());
        dynamics.setAuthorAvatar(user.getAvatarUrl());
        if (dynamic.getMusicId() != null){
            Music music = musicClient.queryMusicById(dynamic.getMusicId());
            dynamics.setMusic(music);
        }
        return dynamics;
    }
}
