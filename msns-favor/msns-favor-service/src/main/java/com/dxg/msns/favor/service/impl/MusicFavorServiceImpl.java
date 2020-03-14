package com.dxg.msns.favor.service.impl;

import com.dxg.msns.favor.mapper.MusicFavorMapper;
import com.dxg.msns.favor.pojo.DynamicFavor;
import com.dxg.msns.favor.service.MusicFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class MusicFavorServiceImpl implements MusicFavorService {
    @Autowired
    private MusicFavorMapper musicFavorMapper;
    /**
     * 查询用户搜藏音乐的总数
     *
     * @param uid
     * @return
     */
    @Override
    public Integer queryCountByUid(String uid) {
        Example example = new Example(DynamicFavor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicId",uid);
        Integer counts = musicFavorMapper.selectCountByExample(example);
        return counts;
    }
}
