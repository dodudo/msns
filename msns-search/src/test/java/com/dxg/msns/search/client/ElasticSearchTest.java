package com.dxg.msns.search.client;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.search.MSNSSearchService;
import com.dxg.msns.search.pojo.Dynamics;
import com.dxg.msns.search.reponsitory.DynamicsRepository;
import com.dxg.msns.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MSNSSearchService.class)

public class ElasticSearchTest {
    @Autowired
    private DynamicsRepository dynamicsRepository;
    @Autowired
    private DynamicsClient dynamicsClient;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private SearchService searchService;
    @Test
    public void createIndex(){
        //创建索引
        this.elasticsearchTemplate.createIndex(Dynamics.class);
        //配置映射
        this.elasticsearchTemplate.putMapping(Dynamics.class);
        Integer page = 1;
        Integer rows = 100;
        //循环查询dynamic，将dynamic变成dynamics，写入索引库
        do {
            //分页查询所有动态
            PageResult<Dynamic> dynamicPageResult = this.dynamicsClient.queryDynamicsByPage(null, page, rows, null, null, true);
            //遍历动态转换为List<Dynamics>
            List<Dynamics> dynamicsList = dynamicPageResult.getItems().stream().map(dynamic -> {
                try {
                    return this.searchService.buildDynamics(dynamic);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            //将转换后的结果保存到索引库
            this.dynamicsRepository.saveAll(dynamicsList);
            rows = dynamicPageResult.getItems().size();
            //每次循环页码加一
            page++;
        }while (rows==100); //直到rows不等于100时结束
    }
}
