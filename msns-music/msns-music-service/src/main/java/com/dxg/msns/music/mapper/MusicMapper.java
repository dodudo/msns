package com.dxg.msns.music.mapper;

import com.dxg.msns.music.pojo.Music;
import com.dxg.msns.music.pojo.TypeStatistics;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MusicMapper extends Mapper<Music> {
    @Select("select type_name,count(*) as music_count from music as m left join music_type as mt on m.music_type_id = mt.id GROUP BY type_name")
    @Results(id = "typeStatisticsMap",value = {
            @Result(column = "type_name",property = "typeName"),
            @Result(column = "music_count",property = "musicCount")
    })
    List<TypeStatistics> queryTypeStatistics();


}
