package com.dxg.msns.favor.service.impl;

import com.dxg.msns.favor.mapper.MusicFavorMapper;
import com.dxg.msns.favor.pojo.DynamicFavor;
import com.dxg.msns.favor.pojo.MusicFavor;
import com.dxg.msns.favor.service.MusicFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        criteria.andEqualTo("collectorId",uid);
        Integer counts = musicFavorMapper.selectCountByExample(example);
        return counts;
    }

    /**
     * 根据用户id查询该用户收藏音乐的id
     *
     * @param uid
     * @return
     */
    @Override
    public Integer[] queryByUid(String uid) {
        Example example = new Example(DynamicFavor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("collectorId",uid);
        List<MusicFavor> musicFavors = musicFavorMapper.selectByExample(example);
        List<Integer> ids = new ArrayList<>();
        for (MusicFavor musicFavor : musicFavors) {
            ids.add(musicFavor.getMusicId());
        }
        Integer[] idsInt = ids.toArray(new Integer[ids.size()]);
        return idsInt;
    }

    /**
     * 删除收藏
     *
     * @param musicFavor
     */
    @Override
    public void deleteFavor(MusicFavor musicFavor) {
//        Example example = new Example(MusicFavor.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("collectorId",musicFavor.getCollectorId()).andEqualTo("musicId()",musicFavor.getMusicId());
        this.musicFavorMapper.delete(musicFavor);
    }

    /**
     * 添加收藏
     *
     * @param musicFavor
     */
    @Override
    public void addFavor(MusicFavor musicFavor) {
        Date date = new Date();
        musicFavor.setFavorDate(date);
        this.musicFavorMapper.insertSelective(musicFavor);
    }
}
