package com.dxg.msns.search.service.impl;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.favor.pojo.DynamicFavor;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.search.client.*;
import com.dxg.msns.search.pojo.Dynamics;
import com.dxg.msns.search.pojo.SearchRequst;
import com.dxg.msns.search.reponsitory.DynamicsRepository;
import com.dxg.msns.search.service.SearchService;
import com.dxg.msns.user.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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

    @Autowired
    private DynamicsRepository dynamicsRepository;

    @Override
    public Dynamics buildDynamics(Dynamic dynamic) {

        User user = userClient.queryNameAvatarById(dynamic.getUid());
        String type = dynamicTypeClient.queryNameById(dynamic.getDynamicTypeId());
        Dynamics dynamics = new Dynamics();
        dynamics.setId(dynamic.getId());
        dynamics.setAll(type + dynamic.getDynamicContent() + user.getUname());
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
        if (dynamic.getMusicId() != null) {
            Music music = musicClient.queryMusicById(dynamic.getMusicId());
            dynamics.setMusic(music);
        }
        return dynamics;
    }

    /**
     * 根据条件查找动态
     *
     * @return
     */
    @Override
    public PageResult<Dynamics> searchDynamics(SearchRequst request) {
        String key = request.getKey();
        //构建查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if (StringUtils.isNotBlank(key)) {
            // 对key进行全文检索查询
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("all", key).operator(Operator.AND));
        }
        //排序
        String sortBy = request.getSortBy();
        Boolean desc = request.getDesc();
        if (StringUtils.isNotBlank(sortBy)) {
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(sortBy).order(desc ? SortOrder.DESC : SortOrder.ASC));
        }
        //分页
        int page = request.getPage();
        int size = request.getSize();
        nativeSearchQueryBuilder.withPageable(PageRequest.of(page - 1, size));

        //查询，获取结果
        Page<Dynamics> dynamicsPage = this.dynamicsRepository.search(nativeSearchQueryBuilder.build());

        return new PageResult<>(dynamicsPage.getTotalElements(), dynamicsPage.getTotalPages(), dynamicsPage.getContent());
    }
}
