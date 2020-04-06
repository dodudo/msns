package com.dxg.msns.like.mapper;

import com.dxg.msns.like.pojo.Like;
import org.apache.commons.lang.ArrayUtils;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface LikeMapper extends Mapper<Like> {
    /**
     * 根据ids修改状态
     *
     * @param ids
     * @param status
     */
    @UpdateProvider(type = Provider.class, method = "batchUpdate")
    void updateStateByIds(Integer[] ids, String status);

    class Provider {
        //批量修改
        public String batchUpdate(Map map) {
            Integer[] ids = (Integer []) map.get("param1");
            String status = (String) map.get("param2");
            StringBuilder sb = new StringBuilder();
            if (ArrayUtils.isEmpty(ids)) {  //全选
                sb.append("update like set status = '" + status + "' where status != '-1' and status != '0'");
            } else {
                sb.append("update like set status = '" + status + "' where status != '-1' and status != '0' and id in (");
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
