package com.dxg.msns.search.client;

import com.dxg.msns.music.api.MusicTypeApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("music-service")
public interface MusicTypeClient extends MusicTypeApi {
}
