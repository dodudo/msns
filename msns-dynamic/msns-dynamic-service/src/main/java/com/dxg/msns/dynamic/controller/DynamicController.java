package com.dxg.msns.dynamic.controller;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.dynamic.pojo.DynamicType;
import com.dxg.msns.dynamic.service.DynamicService;
import com.dxg.msns.dynamic.service.DynamicTypeService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DynamicController {
    @Autowired
    private DynamicService dynamicService;

    /**
     * 条件查询所有分类
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @RequestMapping("page")
    public ResponseEntity<PageResult<Dynamic>> queryDynamicsByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy[],
            @RequestParam(value = "desc",required = false)Boolean desc[],
            @RequestParam(value = "isAll",required = false)Boolean isAll,
            @RequestParam(value = "uids",required = false)String[] uids,
            @RequestParam(value = "ids",required = false)Integer[] ids
    ){
        if (page<0 ||rows<0){
            return ResponseEntity.badRequest().build();
        }
        PageResult<Dynamic> result = this.dynamicService.queryDynamicsByPage(key,page,rows,sortBy,desc,isAll,uids,ids);

        return ResponseEntity.ok(result);
    }


    /**
     * 修改
     * @param dynamic
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateType(@RequestBody Dynamic dynamic){
//        System.out.println("controller::::::"+dynamicType);
        this.dynamicService.updateDynamic(dynamic);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * 删除
     * @param dynamic
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteType(@RequestBody Dynamic dynamic){
        this.dynamicService.deleteDynamic(dynamic);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @RequestMapping("/queryById/{id}")
    public ResponseEntity<Dynamic> queryById(@PathVariable("id") Integer id){
        Dynamic dynamic = this.dynamicService.queryById(id);
        if (dynamic == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(dynamic);
    }

    /**
     * 根据用户id统计该用户发的动态数量
     * @param uid
     * @return
     */
    @RequestMapping("/countByUid/{uid}")
    public ResponseEntity<Integer> queryCountByUid(@PathVariable("uid") String uid){
        Integer dynamicCount = this.dynamicService.queryCountByUid(uid);
        if (dynamicCount == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(dynamicCount);
    }
    /**
     * 新增动态
     * @param dynamic
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addDynamic(@RequestBody Dynamic dynamic){
//       System.out.println("controller::::::"+dynamic);
        this.dynamicService.addDynamic(dynamic);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
