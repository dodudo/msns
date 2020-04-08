package com.dxg.msns.comment.mapper;

import com.dxg.msns.comment.pojo.Comment;
import org.apache.commons.lang.ArrayUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.Map;

public interface CommentMapper extends Mapper<Comment> {
    @Select("select max(comment_date) from comment where status = '1' and dynamic_id = #{dynamic_id} group by dynamic_id")
    Date queryRecentCreateDateByDynamicId(@Param("dynamic_id") Integer dynamicId);

    /**
     * 根据id改变状态(删除)
     *
     * @param id
     */
    @Update("update comment set status = '-1' where id = #{id}")
    void updateDeleteStateById(@Param("id") Long id);

    /**
     * 根据ids修改状态
     *
     * @param ids
     * @param status
     */
    @UpdateProvider(type = Provider.class, method = "batchUpdate")
    void updateStateByIds(Long[] ids, String status,String respondentId);

    class Provider {
        //批量修改
        public String batchUpdate(Map map) {
            Long[] ids = (Long[]) map.get("param1");
            String status = (String) map.get("param2");
            String respondentId = (String) map.get("param3");
            StringBuilder sb = new StringBuilder();
            if (ArrayUtils.isEmpty(ids)) {
                sb.append("update comment set status = '" + status + "' where respondent_id = '"+respondentId+"' and status != '-1' and status != '0'");
            } else {
                sb.append("update comment set status = '" + status + "' where respondent_id = '"+respondentId+"' and status != '-1' and status != '0' and id in (");
                for (int i = 0; i < ids.length; i++) {
                    sb.append(ids[i]);
                    if (i < ids.length - 1) {
                        sb.append(",");
                    }
                }
                sb.append(")");
            }
            return sb.toString();
        }
    }
}
