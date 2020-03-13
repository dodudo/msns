package com.dxg.msns.comment.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public interface CommentApi {
    @RequestMapping("counts/{id}")
    public Integer queryCountsByDynamicId(@PathVariable("id")String dynamicId);
}
