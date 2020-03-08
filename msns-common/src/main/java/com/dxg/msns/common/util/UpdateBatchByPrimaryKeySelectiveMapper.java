package com.dxg.msns.common.util;

import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface UpdateBatchByPrimaryKeySelectiveMapper<T> {
    /**
     * 根据Example条件批量更新实体`record`包含的不是null的属性值
     *
     * @return
     */
    @UpdateProvider(type = BatchExampleProvider.class, method = "dynamicSQL")
    Integer updateBatchByPrimaryKeySelective(List<? extends T> recordList);
}
