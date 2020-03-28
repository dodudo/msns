package com.dxg.msns.search.service.impl;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.search.client.*;
import com.dxg.msns.search.pojo.Dynamics;
import com.dxg.msns.search.pojo.SearchRequst;
import com.dxg.msns.search.reponsitory.DynamicsRepository;
import com.dxg.msns.search.service.DynamicSearchService;
import com.dxg.msns.user.pojo.User;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class DynamicSearchServiceImpl implements DynamicSearchService {

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
        dynamics.setDynamicId(dynamic.getDynamicId());
        dynamics.setAll(type + dynamic.getDynamicContent() + user.getUname());
        dynamics.setTypeId(dynamic.getDynamicTypeId());
        dynamics.setPublishDate(dynamic.getPublishDate());
        dynamics.setRecentCommentDate(commentClient.queryRecentCreateDateByDynamicId(dynamic.getDynamicId()));
        dynamics.setDynamicContent(dynamic.getDynamicContent());
        dynamics.setViewCount(dynamic.getViewCount());
        dynamics.setLikeCount(likeClient.queryCountsByDynamicId(dynamic.getId()));
        dynamics.setFavorCount(dynamicFavorClient.queryByDynamicId(dynamic.getId()));
        dynamics.setCommentCount(commentClient.queryCountsByDynamicId(dynamic.getDynamicId()));
        dynamics.setImgUrls(dynamic.getImgUrls());
        dynamics.setUid(dynamic.getUid());
        dynamics.setAuthor(user.getUname());
        dynamics.setAuthorAvatar(user.getAvatarUrl());
        dynamics.setStatus(dynamic.getStatus());
        if (dynamic.getMusicId() != null) {
            Music music = musicClient.queryMusicByMusicId(dynamic.getMusicId());
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
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        TermQueryBuilder statusBuilder = QueryBuilders.termQuery("status", "1");
        boolQueryBuilder.must(statusBuilder);
        //对key进行全文检索查询
        if (StringUtils.isNotBlank(key)) {
            MatchQueryBuilder allBuilder = QueryBuilders.matchQuery("all", key).operator(Operator.AND);
            boolQueryBuilder.must(allBuilder);
        }
//        System.out.println(request);
        if (ArrayUtils.isNotEmpty(request.getUids())) {

                boolQueryBuilder.must(QueryBuilders.termsQuery("uid", request.getUids()));

        }
        if (ArrayUtils.isNotEmpty(request.getIds())) {

                boolQueryBuilder.must(QueryBuilders.termsQuery("id", request.getIds()));
        }
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
        //过滤
//        nativeSearchQueryBuilder.withFilter(QueryBuilders.termQuery("status","1"));
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
//        System.out.println("查询结果::::::"+dynamicsPage.getContent());
        return new PageResult<>(dynamicsPage.getTotalElements(), dynamicsPage.getTotalPages(), dynamicsPage.getContent());
    }

    @Override
    public void createDynamicIndex(Integer id) {
        Dynamic dynamic = this.dynamicsClient.queryById(id);
        Dynamics dynamics = this.buildDynamics(dynamic);

        //保存到索引库
        this.dynamicsRepository.save(dynamics);
    }

    /**
     * 删除索引
     *
     * @param id
     */
    @Override
    public void deleteDynamicIndex(Integer id) {
        this.dynamicsRepository.deleteById(id);
    }
}
