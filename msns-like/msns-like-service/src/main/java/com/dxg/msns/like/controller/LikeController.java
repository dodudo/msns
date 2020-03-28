package com.dxg.msns.like.controller;

import com.dxg.msns.like.pojo.Like;
import com.dxg.msns.like.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Like)表控制层
 *
 * @author makejava
 * @since 2020-03-13 21:04:50
 */
@RestController
public class LikeController {
    /**
     * 服务对象
     */
    @Resource
    private LikeService likeService;

    /**
     * 查询动态的点赞数
     *
     */
    @GetMapping("queryCountsByDynamicId/{id}")
    public ResponseEntity<Integer> queryCountsByDynamicId(@PathVariable("id") Integer dynamicId) {
        Integer counts = this.likeService.queryCountsByDynamicId(dynamicId);
        if (counts == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(counts);
    }

    /**
     * 新增点赞
     *
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addLike(@RequestBody Like like) {
        likeService.addLike(like);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("queryIsLike")
    public ResponseEntity<List<Like>> queryIsLike(@RequestParam("dynamicIds")Integer[] dynamicIds,@RequestParam("likerId")String likerId){
        List likeList = likeService.queryIsLike(dynamicIds,likerId);
        return ResponseEntity.ok(likeList);
    }
    /**
     * 删除点赞信息
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteLike(Like like) {

        this.likeService.deleteLike(like);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}