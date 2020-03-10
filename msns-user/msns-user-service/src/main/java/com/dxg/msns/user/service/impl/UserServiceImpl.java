package com.dxg.msns.user.service.impl;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.common.util.UnderlineHump;
import com.dxg.msns.user.mapper.UserMapper;
import com.dxg.msns.user.pojo.User;
import com.dxg.msns.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 分页查询用户
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
    public PageResult<User> queryUsersByPage(String key, Integer page, Integer rows, String[] sortBy, Boolean[] desc, Boolean isAll) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (isAll) {
            criteria.andNotEqualTo("status", "-1");
        } else {
            criteria.andEqualTo("status", "-1");
        }
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("uname", "%" + key + "%");
        }
        //添加分页条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (ArrayUtils.isNotEmpty(sortBy)) {
            example.setOrderByClause(UnderlineHump.HumpToUnderline(sortBy[0]) + " " + (desc[0] ? "desc" : "asc"));
        }
        List<User> users = this.userMapper.selectByExample(example);
        PageInfo<User> userPageInfo = new PageInfo<>(users);

        return new PageResult<>(userPageInfo.getTotal(), userPageInfo.getList());
    }

    /**
     * 根据用户名查询用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean findUserByName(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uname",user.getUname());
        List<User> users = this.userMapper.selectByExample(example);
//        System.out.println(users);
        if (users.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 新增用户
     *
     * @param user
     */
    @Override
    public void saveUser(User user) {
        user.setUid(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        user.setStatus("1");
        this.userMapper.insertSelective(user);
    }

    /**
     * 修改用户
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", user.getId());
        this.userMapper.updateByExampleSelective(user, example);
    }

    /**
     * 删除用户
     *
     * @param user
     */
    @Override
    public void deleteUser(User user) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",user.getId());
        this.userMapper.deleteByExample(example);
    }
}
