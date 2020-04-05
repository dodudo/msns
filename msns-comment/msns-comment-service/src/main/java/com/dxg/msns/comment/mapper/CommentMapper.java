package com.dxg.msns.comment.mapper;

import com.dxg.msns.comment.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;

public interface CommentMapper extends Mapper<Comment> {
    @Select("select max(comment_date) from comment where status = '1' and dynamic_id = #{dynamic_id} group by dynamic_id")
    Date queryRecentCreateDateByDynamicId(@Param("dynamic_id") Integer dynamicId);

    /**
     * 根据id改变状态
     * @param id
     */
    @Update("update comment set status = '-1' where id = #{id}")
    void updateStateById(@Param("id")Long id);
}
