package com.dxg.msns.dynamic.service;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.dynamic.pojo.DynamicType;
import org.springframework.stereotype.Service;


public interface DynamicService {
    /**
     * 查询所有动态
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param isAll
     * @return
     */
    PageResult<Dynamic> queryDynamicsByPage(String key, Integer page, Integer rows, String[] sortBy, Boolean[] desc, Boolean isAll,String[] uids,Integer[] ids);

    /**
     * 更新动态
     * @param dynamic
     */
    void updateDynamic(Dynamic dynamic);

    /**
     * 删除动态
     * @param dynamic
     */
    void deleteDynamic(Dynamic dynamic);

    /**
     * 根据id查找动态
     * @param id
     * @return
     */
    Dynamic queryById(Integer id);

    /**
     * 根据用户id查询条数
     * @param uid
     * @return
     */
    Integer queryCountByUid(String uid);
}
