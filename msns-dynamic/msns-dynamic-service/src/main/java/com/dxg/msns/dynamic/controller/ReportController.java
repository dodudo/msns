package com.dxg.msns.dynamic.controller;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.dynamic.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {
    @Autowired
    private ReportService reportService;
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
    public ResponseEntity<PageResult<Dynamic>> queryReportsByPage(
            @RequestParam(value = "key",defaultValue = "")String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",defaultValue = "id")String sortBy[],
            @RequestParam(value = "desc",defaultValue = "true")Boolean desc[],
            @RequestParam(value = "isAll",required = false)Boolean isAll
    ){
        if (page<0 ||rows<0){
            return ResponseEntity.badRequest().build();
        }
        PageResult<Dynamic> result = this.reportService.queryReportsByPage(key,page,rows,sortBy,desc,isAll);

        return ResponseEntity.ok(result);
    }
}
