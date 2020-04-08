package com.dxg.msns.like.controller;

import com.dxg.msns.common.pojo.PageResult;
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
     */
    @GetMapping("queryCountsByDynamicId/{id}")
    public ResponseEntity<Integer> queryCountsByDynamicId(@PathVariable("id") Integer dynamicId) {
        Integer counts = this.likeService.queryCountsByDynamicId(dynamicId);
        if (counts == null) {
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
    public ResponseEntity<List<Like>> queryIsLike(@RequestParam("dynamicIds") Integer[] dynamicIds, @RequestParam("likerId") String likerId) {
        List likeList = likeService.queryIsLike(dynamicIds, likerId);
        return ResponseEntity.ok(likeList);
    }

    /**
     * 删除点赞信息
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteLike(@RequestBody Like like) {

        this.likeService.deleteLike(like);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("page")
    public ResponseEntity<PageResult<Like>> queryByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy[],
            @RequestParam(value = "desc", required = false) Boolean desc[],
            @RequestBody Like like
    ) {
        if (like.getDynamicAuthorid() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        PageResult<Like> commentPageResult = this.likeService.queryByPage(page, rows, sortBy, desc, like);
        if (commentPageResult == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(commentPageResult);
    }
    /**
     * 根据ids修改状态为2（已读）
     */
    @GetMapping("updateStateById")
    public ResponseEntity<Void> updateStateById(@RequestParam("ids")Integer[] ids,@RequestParam("status") String status,@RequestParam("dynamicAuthorid") String dynamicAuthorid){
        likeService.updateStateByIds(ids,status,dynamicAuthorid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据id查询未读次数
     * @param dynamicAuthorid
     * @return
     */
    @GetMapping("countsByDynamicAuthorid/{dynamicAuthorid}")
    public ResponseEntity<Integer> queryCountsByDynamicAuthorid(@PathVariable("dynamicAuthorid")String dynamicAuthorid){
        Integer counts = likeService.queryCountsByDynamicAuthorid(dynamicAuthorid);
        if (counts == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(counts);
    }
}