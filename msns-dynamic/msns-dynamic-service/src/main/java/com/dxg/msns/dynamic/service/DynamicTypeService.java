package com.dxg.msns.dynamic.service;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.DynamicType;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DynamicTypeService {
    PageResult<DynamicType> queryDynamicTypesByPage(String key, Integer page, Integer rows, String sortBy[], Boolean desc[],Boolean isAll);

    /**
     * 新增分类
     * @param dynamicType
     */
    void addType(DynamicType dynamicType);

    /**
     * 根据id更新分类
     * @param dynamicType
     */
    void updateType(DynamicType dynamicType);

    /**
     * 删除类型
     * @param dynamicType
     */
    void deleteType(DynamicType dynamicType);
}
