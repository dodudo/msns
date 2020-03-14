package com.dxg.msns.dynamic.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("category")
public interface DynamicTypeApi {
    /**
     * 查询所有分类名
     * @return
     */
    @RequestMapping("names")
    List<String> queryAllDynamiTypeNames();
    /**
     * 根据id查询名称
     * @param id
     * @return
     */
    @RequestMapping("queryNameById/{id}")
    public String queryNameById(@PathVariable("id") Integer id);
}
