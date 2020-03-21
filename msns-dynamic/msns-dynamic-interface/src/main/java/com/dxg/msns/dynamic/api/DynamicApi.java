package com.dxg.msns.dynamic.api;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityResult;


public interface DynamicApi {
    /**
     * 分页查询所有分类
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @RequestMapping("page")
    public PageResult<Dynamic> queryDynamicsByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy[],
            @RequestParam(value = "desc",required = false)Boolean desc[],
            @RequestParam(value = "isAll",required = false)Boolean isAll,
             @RequestParam(value = "uids",required = false)String[] uids,
            @RequestParam(value = "ids",required = false)Integer[] ids
    );
    /**
     * 根据id查找
     * @param id
     * @return
     */
    @RequestMapping("/queryById/{id}")
    public Dynamic queryById(@PathVariable("id") Integer id);

    /**
     * 根据用户id统计该用户发的动态数量
     * @param uid
     * @return
     */
    @RequestMapping("/countByUid/{uid}")
    public Integer queryCountByUid(@PathVariable("uid") String uid);
}
