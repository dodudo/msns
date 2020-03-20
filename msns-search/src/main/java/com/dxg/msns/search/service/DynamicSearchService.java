package com.dxg.msns.search.service;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.search.pojo.Dynamics;
import com.dxg.msns.search.pojo.SearchRequst;

public interface DynamicSearchService {
    public Dynamics buildDynamics(Dynamic dynamic);

    /**
     * 根据条件查找动态
     * @param requst
     * @return
     */
    PageResult<Dynamics> searchDynamics(SearchRequst requst);

    /**
     * 修改索引
     * @param id
     */
    void createDynamicIndex(Integer id);

    /**
     * 删除索引
     * @param id
     */
    void deleteDynamicIndex(Integer id);
}
