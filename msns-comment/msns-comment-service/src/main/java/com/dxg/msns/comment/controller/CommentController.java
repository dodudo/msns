package com.dxg.msns.comment.controller;

import com.dxg.msns.comment.pojo.Comment;
import com.dxg.msns.comment.service.CommentService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping("byDynamicId/{id}")
    public ResponseEntity<List<Comment>> queryByDynamicId(@PathVariable("id")String dynamicId){
        List<Comment> comments = commentService.queryByDynamicId(dynamicId);
        if (CollectionUtils.isEmpty(comments)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comments);
    }

    @RequestMapping("counts/{id}")
    public ResponseEntity<Integer> queryCountsByDynamicId(@PathVariable("id")String dynamicId){
        Integer counts = commentService.queryCountsByDynamicId(dynamicId);
        if (counts == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(counts);
    }

    /**
     * 根据动态id查询最新评论时间
     * @param dynamicId
     * @return
     */
    @RequestMapping("recent/{id}")
    public ResponseEntity<Date> queryRecentCreateDateByDynamicId(@PathVariable("id")String dynamicId){
        Date date = commentService.queryRecentCreateDateByDynamicId(dynamicId);
        if (date == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(date);
    }

    @RequestMapping("queryByUid/{uid}")
    public ResponseEntity<List<Comment>> queryByUid(@PathVariable("uid")String uid){
        List<Comment> comments = commentService.queryByUid(uid);
        if (comments.size() == 0 || comments == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comments);
    }
}
