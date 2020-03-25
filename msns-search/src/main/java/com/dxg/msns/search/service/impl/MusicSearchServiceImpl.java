package com.dxg.msns.search.service.impl;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.search.client.MusicClient;
import com.dxg.msns.search.client.MusicTypeClient;
import com.dxg.msns.search.pojo.Dynamics;
import com.dxg.msns.search.pojo.Musics;
import com.dxg.msns.search.pojo.SearchRequst;
import com.dxg.msns.search.reponsitory.MusicRepository;
import com.dxg.msns.search.service.MusicSearchService;
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
public class MusicSearchServiceImpl implements MusicSearchService {

    @Autowired
    private MusicClient musicClient;

    @Autowired
    private MusicTypeClient musicTypeClient;

    @Autowired
    private MusicRepository musicRepository;

    /**
     * 构建Musics
     *
     * @param music
     * @return
     */
    @Override
    public Musics buildMusic(Music music) {
        Musics musics = new Musics();
        //设置id
        musics.setId(music.getId());
        //设置音乐专辑
        musics.setMusicAlbum(music.getMusicAlbum());
        //设置音乐作者
        musics.setMusicAuthor(music.getMusicAuthor());
        //设置音乐封面
        musics.setMusicCoverUrl(music.getMusicCoverUrl());
        //设置音乐id
        musics.setMusicId(music.getMusicId());
        //设置音乐时长
        musics.setMusicLength(music.getMusicLength());
        //设置音乐名
        musics.setMusicName(music.getMusicName());
        //设置发布日期
        musics.setMusicPublisDate(music.getMusicPublisDate());
        //设置上传日期
        musics.setMusicPushDate(music.getMusicPushDate());
        //获取音乐类型id
        musics.setMusicTypeId(music.getMusicTypeId());
        //设置音乐路径
        musics.setMusicUrl(music.getMusicUrl());
        //设置状态
        musics.setStatus(music.getStatus());
        //设置查询key
        musics.setAll(music.getMusicName()+music.getMusicAuthor()+music.getMusicAlbum()+musicTypeClient.queryById(music.getMusicTypeId()).getTypeName());

        return musics;
    }

    /**
     * 查询音乐
     *
     * @param request
     * @return
     */
    @Override
    public PageResult<Musics> searchMusic(SearchRequst request) {
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
        if (request.getTypeId() != null){
            boolQueryBuilder.must(QueryBuilders.termQuery("typeId", request.getTypeId()));
        }
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);
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
        Page<Musics> musicsPage = this.musicRepository.search(nativeSearchQueryBuilder.build());

        return new PageResult<>(musicsPage.getTotalElements(), musicsPage.getTotalPages(), musicsPage.getContent());
    }

    /**
     * 新增修改音乐
     *
     * @param id
     */
    @Override
    public void createMusicIndex(Integer id) {

    }

    /**
     * 删除音乐索引
     *
     * @param id
     */
    @Override
    public void deleteMusicIndex(Integer id) {

    }
}
