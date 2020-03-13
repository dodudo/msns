package com.dxg.msns.favor.service.impl;

import com.dxg.msns.favor.mapper.DynamicFavorMapper;
import com.dxg.msns.favor.pojo.DynamicFavor;
import com.dxg.msns.favor.service.DynamicFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class DynamicFavorServiceImpl implements DynamicFavorService {
    @Autowired
    private DynamicFavorMapper dynamicFavorMapper;
    /**
     * 查询动态的收藏数
     *
     * @param dynamicId
     * @return
     */
    @Override
    public Integer queryByDynamicId(String dynamicId) {
        Example example = new Example(DynamicFavor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamic_id",dynamicId);
        Integer counts = dynamicFavorMapper.selectCountByExample(example);
        return counts;
    }
}
