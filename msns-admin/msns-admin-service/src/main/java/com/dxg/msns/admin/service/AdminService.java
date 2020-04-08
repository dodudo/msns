package com.dxg.msns.admin.service;

import com.dxg.msns.admin.pojo.Admin;
import com.dxg.msns.auth.entity.UserInfo;
import com.dxg.msns.common.pojo.PageResult;

import java.util.List;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2020-03-10 10:54:36
 */
public interface AdminService {

    /**
     * 分页查询数据
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param isAll
     * @return
     */
    PageResult<Admin> queryAdminsByPage(String key, Integer page, Integer rows, String[] sortBy, Boolean[] desc, Boolean isAll);

    /**
     * 更新管理员
     * @param admin
     */
    void updateAdmin(Admin admin);

    /**
     * 删除管理员
     * @param admin
     */
    void deleteAdmin(Admin admin);

    /**
     * 根据昵称查找管理员
     * @param admin
     * @return
     */
    boolean findAdminByName(Admin admin);

    /**
     * 新增管理员
     * @param admin
     */
    void saveAdmin(Admin admin);

    /**
     * 验证用户名和密码
     * @param aname
     * @param apassword
     * @return
     */
    String authentication(String aname, String apassword);

    /**
     * 根据用户信息查询管理员是否存在
     * @param userInfo
     * @return
     */
    Admin getAdmin(UserInfo userInfo);
}