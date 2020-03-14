package com.dxg.msns.search.client;

import com.dxg.msns.comment.api.CommentApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("comment-service")
public interface CommentClient extends CommentApi {
}
