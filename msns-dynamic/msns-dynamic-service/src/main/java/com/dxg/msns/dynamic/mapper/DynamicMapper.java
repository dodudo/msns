package com.dxg.msns.dynamic.mapper;

import com.dxg.msns.dynamic.pojo.Dynamic;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface DynamicMapper extends Mapper<Dynamic> {

    /**
     * 查询所有被举报的
     * @param key
     * @param sortBy
     * @param isDesc
     * @return
     */
    @Select("select * from dynamic where status <> '-1' and dynamic_content like #{key} order by ${sortBy} ${isDesc} ")
    @Results(id = "dynamicMap",
    value = {
        @Result(id = true,column = "id",property = "id"),
            @Result(column = "dynamic_id",property = "dynamicId"),
            @Result(column = "dynamic_content",property = "dynamicContent"),
            @Result(column = "uname",property = "uname"),
            @Result(column = "publish_date",property = "publishDate"),
            @Result(column = "report_count",property = "reportCount"),
            @Result(column = "dynamic_id",property = "reports",many = @Many(
                    select = "com.dxg.msns.dynamic.mapper.ReportMapper.findById"
            )),
            @Result(column = "dynamic_id",property = "commentCount",one = @One(
                    select = "com.dxg.msns.dynamic.mapper.dynamicMapper.findCommentCountByDynamicId"
            ))
    })
    List<Dynamic> selectAllReports(String key, String sortBy, String isDesc);

    /**
     * 计算举报量插入动态reports字段
     */
    @Update("update dynamic set report_count = #{reportCount} where dynamic_id = #{dynamicId}")
    @ResultMap(value = "dynamicMap")
    void insertReportCount(String dynamicId,Integer reportCount);


}
