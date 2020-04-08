package com.dxg.msns.admin.controller;

import com.dxg.msns.admin.pojo.Admin;
import com.dxg.msns.admin.properties.AdminJwtProperties;
import com.dxg.msns.admin.service.AdminService;
import com.dxg.msns.auth.entity.UserInfo;
import com.dxg.msns.auth.utils.CookieUtils;
import com.dxg.msns.auth.utils.JwtUtils;
import com.dxg.msns.common.pojo.PageResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2020-03-10 10:54:38
 */
@RestController
@EnableConfigurationProperties(AdminJwtProperties.class)
public class AdminController {
    /**
     * 服务对象
     */
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminJwtProperties adminJwtProperties;


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

    /**
     * 验证登录信息
     * @param aname
     * @param apassword
     * @param request
     * @param response
     * @return
     */
    @PostMapping("accredit")
    public ResponseEntity<Void> authentication(@RequestParam("aname") String aname,
                                               @RequestParam("apassword") String apassword,
                                               HttpServletRequest request,
                                               HttpServletResponse response) {
        //登录校验，生成token
        String token = this.adminService.authentication(aname,apassword);
        if (StringUtils.isEmpty(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //将token写入cookie，并指定httpOnly为true，防止js获取修改
        CookieUtils.setCookie(request,response,this.adminJwtProperties.getCookieName(),token,this.adminJwtProperties.getCookieMaxAge(),null,true);
        return ResponseEntity.ok().build();
    }

    /**
     * 删除cookie
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("deleteCookie")
    public ResponseEntity<Void> deleteCookie(HttpServletRequest request,HttpServletResponse response) {
        CookieUtils.setCookie(request,response,this.adminJwtProperties.getCookieName(),null,0,null,true);
        return ResponseEntity.ok().build();
    }

    /**
     * 验证登录状态信息
     * @param token
     * @param request
     * @param response
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verifyUser(@CookieValue("MSNS_ADMIN_TOKEN")String token, HttpServletRequest request, HttpServletResponse response){
//        System.out.println("token:::"+token);
        try {
            //从token‘中解析信息
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, this.adminJwtProperties.getPublicKey());
            Admin admin = adminService.getAdmin(userInfo);
            System.out.println(userInfo);
            userInfo.setId(admin.getId());
            userInfo.setUid(admin.getAdminid());
            userInfo.setUname(admin.getAname());
            //解析成功要重新刷新token
            token = JwtUtils.generateToken(userInfo, this.adminJwtProperties.getPrivateKey(), this.adminJwtProperties.getExpire());
            //更新cookie中的token
            CookieUtils.setCookie(request,response,this.adminJwtProperties.getCookieName(),token,this.adminJwtProperties.getCookieMaxAge(),null,true);
            //解析成功返回用户信息
            return  ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}