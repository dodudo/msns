package com.dxg.msns.user.service.impl;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.common.util.CodecUtils;
import com.dxg.msns.common.util.NumberUtils;
import com.dxg.msns.common.util.UUIDUtils;
import com.dxg.msns.common.util.UnderlineHump;
import com.dxg.msns.user.mapper.UserMapper;
import com.dxg.msns.user.pojo.User;
import com.dxg.msns.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;
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

    /**
     * 查询用户数据是否可用
     *
     * @param data
     * @param type
     * @return
     */
    @Override
    public Boolean checkUserData(String data, Integer type) {
        User user = new User();
        switch (type){
            case 1:
                user.setUname(data);
                break;
            case 2:
                user.setPhone(data);
                break;
            case 3:
                user.setEmail(data);
                break;
            default:
                return null;
        }
        return this.userMapper.selectCount(user) == 0;
    }

    /**
     * 根据id查找用户
     *
     * @param uid
     * @return
     */
    @Override
    public User queryNameAvatarById(String uid) {
        return this.userMapper.queryNameAvatarById(uid);
    }

    @Override
    public Boolean sendVerifyCode(String email) {
        //生成验证码
        String code = NumberUtils.generateCode(6);
        try {
            Map<String ,String> msg = new HashMap<>();
            msg.put("email",email);
            msg.put("code",code);
            this.amqpTemplate.convertAndSend("msns.sms.email.exchange","sms.verify.code",msg);
            this.redisTemplate.opsForValue().set(email,code);
            this.redisTemplate.expire(email,5,TimeUnit.MINUTES);
            return true;
        } catch (AmqpException e) {
            e.printStackTrace();
            System.out.println("baocuole");
            return false;
        }
    }

    @Override
    public Map<String,Object> register(User user, String code) {
        String codeCache = this.redisTemplate.opsForValue().get(user.getEmail());
        Map<String,Object> map = new HashMap<>();
        if (!StringUtils.equals(code,codeCache)){
           map.put("boo",false);
           map.put("err","验证码错误！");
           return map;
        }
        //生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        //加密密码
        user.setUpassword(CodecUtils.md5Hex(user.getUpassword(),salt));
        user.setId(null);
        user.setUid(UUIDUtils.getUUID());
        user.setStatus("1");
        boolean b = this.userMapper.insertSelective(user) == 1;

        if (b){
            //注册成功删除redis中的记录
            this.redisTemplate.delete(user.getEmail());
        }else {
            map.put("err","注册失败！");
        }
        map.put("boo",b);
        return map;
    }
}
