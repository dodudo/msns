package com.dxg.msns.search.client;

import com.dxg.msns.search.MSNSSearchService;
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

    @Test
    public void testSelectTypeName(){
        List<String> names = this.dynamicTypeClient.queryAllDynamiTypeNames();
        names.forEach(System.out::println);
    }
}