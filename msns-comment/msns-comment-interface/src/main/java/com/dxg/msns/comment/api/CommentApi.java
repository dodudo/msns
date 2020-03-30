package com.dxg.msns.comment.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

public interface CommentApi {
    @RequestMapping("counts/{id}")
    public Integer queryCountsByDynamicId(@PathVariable("id")Integer dynamicId);
    @RequestMapping("recent/{id}")
    public Date queryRecentCreateDateByDynamicId(@PathVariable("id")Integer dynamicId);
}
