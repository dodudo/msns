package com.dxg.msns.search.service;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.search.pojo.Dynamics;
import com.dxg.msns.search.pojo.SearchRequst;

public interface SearchService {
    public Dynamics buildDynamics(Dynamic dynamic);

    /**
     * 根据条件查找动态
     * @param requst
     * @return
     */
    PageResult<Dynamics> searchDynamics(SearchRequst requst);
}
