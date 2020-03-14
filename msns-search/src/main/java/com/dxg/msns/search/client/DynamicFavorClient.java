package com.dxg.msns.search.client;

import com.dxg.msns.favor.api.DynamicFavorApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("favor-service")
public interface DynamicFavorClient extends DynamicFavorApi {
}
