package com.dxg.msns.search.client;

import com.dxg.msns.like.api.LikeApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("like-service")
public interface LikeClient extends LikeApi {
}
