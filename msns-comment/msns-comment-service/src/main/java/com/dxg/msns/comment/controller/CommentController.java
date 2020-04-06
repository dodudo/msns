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

    /**
     * 分页查询评论
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param isAll
     * @param comment
     * @return
     */
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
     * 根据id查询评论
     * @param id
     * @return
     */
    @RequestMapping("id/{id}")
    public ResponseEntity<Comment> queryById(@PathVariable("id")Integer id){
        Comment comment = commentService.queryById(id);
        if (comment == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment);
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

    /**
     * 根据用户id查询
     * @param uid
     * @return
     */
    @PostMapping("queryByUid/{uid}")
    public ResponseEntity<List<Comment>> queryByUid(@PathVariable("uid")String uid){
        List<Comment> comments = commentService.queryByUid(uid);
        if (comments.size() == 0 || comments == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comments);
    }

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @PostMapping("add")
    public ResponseEntity<Comment> add(@RequestBody Comment comment){
//        System.out.println(comment);
        Comment comment1 = commentService.add(comment);
        return ResponseEntity.ok(comment1);
    }
    /**
     * 根据id修改状态为-1（删除状态）
     */
    @GetMapping("updateStateDeleteById/{id}")
    public ResponseEntity<Void> updateStateDeleteById(@PathVariable("id")Long id,@RequestParam("dynamicId")Integer dynamicId){
        commentService.updateDeleteStateById(id,dynamicId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * 根据ids修改状态为2（已读）
     */
    @GetMapping("updateStateById")
    public ResponseEntity<Void> updateStateById(@RequestParam("ids")Long[] ids,@RequestParam("status") String status){
        commentService.updateStateByIds(ids,status);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据id查询未读次数
     * @param respondentId
     * @return
     */
    @GetMapping("countsByRespondentId/{respondentId}")
    public ResponseEntity<Integer> queryCountsByRespondentId(@PathVariable("respondentId")String respondentId){
        Integer counts = commentService.queryCountsByRespondentId(respondentId);
        if (counts == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(counts);
    }

}
