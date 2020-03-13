package com.dxg.msns.dynamic.mapper;

import com.dxg.msns.dynamic.pojo.DynamicType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DynamicTypeMapper extends Mapper<DynamicType> {
    @Select("select type_name from dynamic_type where type_status='1' ")
    List<String> queryAllDynamiTypeNames();
}
