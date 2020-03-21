package com.dxg.msns.search.client;

import com.dxg.msns.user.api.FollowApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("user-service")
public interface FollowClient extends FollowApi {
}
