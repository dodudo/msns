package com.dxg.msns.dynamic.controller;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.dynamic.pojo.DynamicType;
import com.dxg.msns.dynamic.service.DynamicService;
import com.dxg.msns.dynamic.service.DynamicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DynamicController {
    @Autowired
    private DynamicService dynamicService;

    /**
     * 查询所有分类
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Dynamic>> queryDynamicsByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy[],
            @RequestParam(value = "desc",required = false)Boolean desc[],
            @RequestParam(value = "isAll",required = false)Boolean isAll
    ){
        if (page<0 ||rows<0){
            return ResponseEntity.badRequest().build();
        }
        PageResult<Dynamic> result = this.dynamicService.queryDynamicsByPage(key,page,rows,sortBy,desc,isAll);

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
     * 查询所有被举报的
     */
}
