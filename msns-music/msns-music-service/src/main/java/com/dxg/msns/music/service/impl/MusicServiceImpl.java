package com.dxg.msns.music.service.impl;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.common.util.UnderlineHump;
import com.dxg.msns.music.mapper.MusicMapper;
import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.music.pojo.TypeStatistics;
import com.dxg.msns.music.service.MusicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.provider.ExampleProvider;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicMapper musicMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;
    private void sendMsg(Integer id,String type) {
        //向队列发送消息
        try {
            this.amqpTemplate.convertAndSend("music."+type, id.toString());
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }
    @Override
    public PageResult<Music> queryMusicsByPage(String key, Integer page, Integer rows, String[] sortBy, Boolean[] desc, Boolean isAll) {
        Example example = new Example(Music.class);
        Example.Criteria criteria = example.createCriteria();
        if (isAll) {
            criteria.andNotEqualTo("status", "-1");
        } else {
            criteria.andEqualTo("status", "-1");
        }
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("musicName", "%" + key + "%");
        }
        //添加分页条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (ArrayUtils.isNotEmpty(sortBy)) {
            example.setOrderByClause(UnderlineHump.HumpToUnderline(sortBy[0]) + " " + (desc[0] ? "desc" : "asc"));
        }
        List<Music> musics = this.musicMapper.selectByExample(example);
        PageInfo<Music> musicPageInfo = new PageInfo<>(musics);

        return new PageResult<>(musicPageInfo.getTotal(), musicPageInfo.getList());
    }

    /**
     * 查询音乐分类统计
     *
     * @return
     */
    @Override
    public List<TypeStatistics> queryTypeStatistics() {
        return this.musicMapper.queryTypeStatistics();
    }

    /**
     * 修改音乐
     *
     * @param music
     */
    @Override
    @Transactional
    public void updateMusic(Music music) {
        Example example = new Example(Music.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", music.getId());
        this.musicMapper.updateByExampleSelective(music, example);
        sendMsg(music.getId(),"update");

    }

    /**
     * 添加音乐
     *
     * @param music
     */
    @Override
    @Transactional
    public void saveMusic(Music music) {
        music.setMusicId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        Date date = new Date();
        music.setMusicPushDate(date);

        this.musicMapper.insertSelective(music);
        sendMsg(music.getId(),"insert");
    }

    /**
     * 查找是否有相同音乐
     *
     * @param music
     * @return
     */
    @Override
    public boolean findMusicByInfo(Music music) {
        Example example = new Example(Music.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("musicName",music.getMusicName()).andEqualTo("musicAuthor",music.getMusicAuthor()).andEqualTo("musicAlbum",music.getMusicAlbum());
        List<Music> musicList = this.musicMapper.selectByExample(example);
        System.out.println(musicList);
        if (musicList.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 删除音乐
     *
     * @param music
     */
    @Override
    @Transactional
    public void deleteMusic(Music music) {
        Example example = new Example(Music.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",music.getId());
        this.musicMapper.deleteByExample(example);
        sendMsg(music.getId(),"delete");
    }

    /**
     * 根据id查找音乐
     *
     * @param id
     * @return
     */
    @Override
    public Music queryMusicById(Integer id) {
        return  this.musicMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据音乐id查找音乐
     *
     * @param musicId
     * @return
     */
    @Override
    public Music queryMusicByMusicId(String musicId) {
        Example example = new Example(Music.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("musicId",musicId);
        return  this.musicMapper.selectByExample(example).get(0);
    }
}
