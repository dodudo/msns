package com.dxg.msns.admin.controller;

import com.dxg.msns.admin.pojo.Admin;
import com.dxg.msns.admin.service.AdminService;
import com.dxg.msns.common.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2020-03-10 10:54:38
 */
@RestController
public class AdminController {
    /**
     * 服务对象
     */
    @Autowired
    private AdminService adminService;

    /**
     * 查询所有管理员
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Admin>> queryAdminsByPage(
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
        PageResult<Admin> result = this.adminService.queryAdminsByPage(key,page,rows,sortBy,desc,isAll);

        return ResponseEntity.ok(result);
    }
    /**
     * 新增管理员
     * @param admin
     * @return
     */
    @PostMapping
    public ResponseEntity<String> saveAdmin(@RequestBody Admin admin){
        //在插入前查询是否存在该用户名
        boolean hadUser = this.adminService.findAdminByName(admin);
        if (!hadUser){
            return new ResponseEntity("已存在该用户！", HttpStatus.BAD_REQUEST);
        }else {
            this.adminService.saveAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

    }

    /**
     * 修改
     * @param admin
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateAdmin(@RequestBody Admin admin){
//        System.out.println("controller::::::"+dynamicType);

            this.adminService.updateAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    /**
     * 删除
     * @param admin
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAdmin(@RequestBody Admin admin){
        this.adminService.deleteAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}