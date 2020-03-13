package com.dxg.msns.search.reponsitory;

import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.search.pojo.Dynamics;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DynamicsRepository extends ElasticsearchRepository<Dynamics,Integer> {
}
