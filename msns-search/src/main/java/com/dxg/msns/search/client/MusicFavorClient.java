package com.dxg.msns.search.client;

import com.dxg.msns.favor.api.MusicFavorApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("favor-service")
public interface MusicFavorClient extends MusicFavorApi {
}
