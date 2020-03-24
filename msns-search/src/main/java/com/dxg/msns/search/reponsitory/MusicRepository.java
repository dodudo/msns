package com.dxg.msns.search.reponsitory;

import com.dxg.msns.search.pojo.Musics;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MusicRepository extends ElasticsearchRepository<Musics,Integer> {
}
