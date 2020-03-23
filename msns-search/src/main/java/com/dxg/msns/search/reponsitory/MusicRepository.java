package com.dxg.msns.search.reponsitory;

import com.dxg.msns.music.pojo.Music;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MusicRepository extends ElasticsearchRepository<Music,Integer> {
}
