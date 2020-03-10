package com.dxg.msns.admin.service.impl;

import com.dxg.msns.admin.pojo.Admin;
import com.dxg.msns.admin.mapper.AdminMapper;
import com.dxg.msns.admin.service.AdminService;
import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.common.util.UnderlineHump;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2020-03-10 10:54:37
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;


    /**
     * 分页查询数据
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param isAll
     * @return
     */
    @Override
    public PageResult<Admin> queryAdminsByPage(String key, Integer page, Integer rows, String[] sortBy, Boolean[] desc, Boolean isAll) {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        if (isAll) {
            criteria.andNotEqualTo("status", "-1");
        } else {
            criteria.andEqualTo("status", "-1");
        }
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("aname", "%" + key + "%");
        }
        //添加分页条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (ArrayUtils.isNotEmpty(sortBy)) {
            example.setOrderByClause(UnderlineHump.HumpToUnderline(sortBy[0]) + " " + (desc[0] ? "desc" : "asc"));
        }
        List<Admin> admins = this.adminMapper.selectByExample(example);
        PageInfo<Admin> userPageInfo = new PageInfo<>(admins);

        return new PageResult<>(userPageInfo.getTotal(), userPageInfo.getList());
    }

    /**
     * 更新管理员
     *
     * @param admin
     */
    @Override
    public void updateAdmin(Admin admin) {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", admin.getId());
        this.adminMapper.updateByExampleSelective(admin, example);
    }

    /**
     * 删除管理员
     *
     * @param admin
     */
    @Override
    public void deleteAdmin(Admin admin) {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",admin.getId());
        this.adminMapper.deleteByExample(example);
    }

    /**
     * 根据昵称查找管理员
     *
     * @param admin
     * @return
     */
    @Override
    public boolean findAdminByName(Admin admin) {
        Example example = new Example(Admin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("aname",admin.getAname());
        List<Admin> admins = this.adminMapper.selectByExample(example);
//        System.out.println(users);
        if (admins.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 新增管理员
     *
     * @param admin
     */
    @Override
    public void saveAdmin(Admin admin) {
        admin.setAdminid(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        this.adminMapper.insertSelective(admin);
    }
}