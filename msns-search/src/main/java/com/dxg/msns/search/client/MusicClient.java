package com.dxg.msns.search.client;

import com.dxg.msns.music.api.MusicApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("music-service")
public interface MusicClient extends MusicApi {
}
