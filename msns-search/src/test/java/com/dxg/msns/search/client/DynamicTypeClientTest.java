package com.dxg.msns.search.client;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.search.MSNSSearchService;
import com.dxg.msns.search.pojo.Dynamics;
import com.dxg.msns.search.service.SearchService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MSNSSearchService.class)
class DynamicTypeClientTest {
    @Autowired
    private DynamicTypeClient dynamicTypeClient;

    @Autowired
    private DynamicsClient dynamicsClient;
    @Autowired
    private SearchService searchService;
    @Test
    public void testSelectTypeName(){
        List<String> names = this.dynamicTypeClient.queryAllDynamiTypeNames();
        names.forEach(System.out::println);
    }
    @Test
    public void testBuildDynamics(){
        PageResult<Dynamic> dynamicPageResult = dynamicsClient.queryDynamicsByPage("", 1, 100, null, null, true);
        List<Dynamic> items = dynamicPageResult.getItems();
        System.out.println(items.get(0));
        Dynamics dynamics = searchService.buildDynamics(items.get(0));
        System.out.println(dynamics);
    }
}