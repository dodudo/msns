package com.dxg.msns.comment.controller;

import com.dxg.msns.comment.pojo.Comment;
import com.dxg.msns.comment.service.CommentService;
import com.dxg.msns.common.pojo.PageResult;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("page")
    public ResponseEntity<PageResult<Comment>> queryByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy[],
            @RequestParam(value = "desc",required = false)Boolean desc[],
            @RequestParam(value = "isAll",required = false)Boolean isAll,
            @RequestBody Comment comment
    ){
        PageResult<Comment> commentPageResult = this.commentService.queryByPage(key,page,rows,sortBy,desc,isAll,comment);
        if (commentPageResult == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(commentPageResult);
    }

    /**
     * 根据动态id查询评论
     * @param dynamicId
     * @return
     */
    @RequestMapping("byDynamicId/{id}")
    public ResponseEntity<List<Comment>> queryByDynamicId(@PathVariable("id")Integer dynamicId){
        List<Comment> comments = commentService.queryByDynamicId(dynamicId);
        if (CollectionUtils.isEmpty(comments)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comments);
    }

    /**
     * 根据动态id查询动态的评论数
     * @param dynamicId
     * @return
     */
    @RequestMapping("counts/{id}")
    public ResponseEntity<Integer> queryCountsByDynamicId(@PathVariable("id")Integer dynamicId){
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
    public ResponseEntity<Date> queryRecentCreateDateByDynamicId(@PathVariable("id")Integer dynamicId){
        Date date = commentService.queryRecentCreateDateByDynamicId(dynamicId);

        return ResponseEntity.ok(date);
    }

    @PostMapping("queryByUid/{uid}")
    public ResponseEntity<List<Comment>> queryByUid(@PathVariable("uid")String uid){
        List<Comment> comments = commentService.queryByUid(uid);
        if (comments.size() == 0 || comments == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comments);
    }
    @PostMapping("add")
    public ResponseEntity<Comment> add(@RequestBody Comment comment){
//        System.out.println(comment);
        Comment comment1 = commentService.add(comment);
        return ResponseEntity.ok(comment1);
    }

}
