package com.dxg.msns.search.client;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.search.MSNSSearchServiceApplication;
import com.dxg.msns.search.pojo.Dynamics;
import com.dxg.msns.search.service.DynamicSearchService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MSNSSearchServiceApplication.class)
class DynamicTypeClientTest {
    @Autowired
    private DynamicTypeClient dynamicTypeClient;

    @Autowired
    private DynamicsClient dynamicsClient;
    @Autowired
    private DynamicSearchService dynamicSearchService;
    @Test
    public void testSelectTypeName(){
        List<String> names = this.dynamicTypeClient.queryAllDynamiTypeNames();
        names.forEach(System.out::println);
    }
    @Test
    public void testBuildDynamics(){
        PageResult<Dynamic> dynamicPageResult = dynamicsClient.queryDynamicsByPage("", 1, 100, null, null, true,null,null);
        List<Dynamic> items = dynamicPageResult.getItems();
        System.out.println(items.get(0));
        Dynamics dynamics = dynamicSearchService.buildDynamics(items.get(0));
        System.out.println(dynamics);
    }
}