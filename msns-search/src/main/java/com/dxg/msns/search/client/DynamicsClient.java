package com.dxg.msns.search.client;

import com.dxg.msns.dynamic.api.DynamicApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("dynamic-service")
public interface DynamicsClient extends DynamicApi {
}
