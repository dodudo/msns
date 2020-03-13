package com.dxg.msns.search.client;

import com.dxg.msns.dynamic.api.DynamicTypeApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("dynamic-service")
public interface DynamicTypeClient extends DynamicTypeApi {
}
