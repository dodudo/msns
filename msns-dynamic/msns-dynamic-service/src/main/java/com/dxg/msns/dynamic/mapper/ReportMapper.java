package com.dxg.msns.dynamic.mapper;

import com.dxg.msns.dynamic.pojo.Report;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReportMapper {
    @Select("select * from report where dynamic_id = #{dynamicId}")
    @Results(id = "reportMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "dynamic_id",property = "dynamicId"),
            @Result(column = "reason",property = "reason")
    })
    List<Report> findById(@Param("dynamicId") String dynamicId);
}
