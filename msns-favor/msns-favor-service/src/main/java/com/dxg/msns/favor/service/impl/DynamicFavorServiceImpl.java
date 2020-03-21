package com.dxg.msns.favor.service.impl;

import com.dxg.msns.favor.mapper.DynamicFavorMapper;
import com.dxg.msns.favor.pojo.DynamicFavor;
import com.dxg.msns.favor.service.DynamicFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

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
    public Integer queryByDynamicId(Integer dynamicId) {
        Example example = new Example(DynamicFavor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicId",dynamicId);
        Integer counts = dynamicFavorMapper.selectCountByExample(example);
        return counts;
    }

    @Override
    public Integer queryByUid(String uid) {
        Example example = new Example(DynamicFavor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("collectorId",uid);
        Integer counts = dynamicFavorMapper.selectCountByExample(example);
        return counts;
    }

    /**
     * 查询用户关注的所有动态id
     *
     * @param uid
     * @return
     */
    @Override
    public Integer[] queryFavorByUid(String uid) {
        Example example = new Example(DynamicFavor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("collectorId",uid);
        List<DynamicFavor> dynamicFavors = dynamicFavorMapper.selectByExample(example);
        List<Integer> ids = new ArrayList<Integer>();
        for (DynamicFavor dynamicFavor : dynamicFavors) {
            ids.add(dynamicFavor.getDynamicId());
        }
        Integer[] integers = ids.toArray(new Integer[dynamicFavors.size()]);
        return integers;
    }
}
