package com.dxg.msns.dynamic.service.impl;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.mapper.DynamicTypeMapper;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.dynamic.pojo.DynamicType;
import com.dxg.msns.dynamic.service.DynamicTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.provider.ExampleProvider;

import java.util.List;

@Service
public class DynamicTypeServiceImpl implements DynamicTypeService {

    @Autowired
    private DynamicTypeMapper typeMapper;

    /**
     * 查询状态不为-1的动态分类
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @Override
    public PageResult<DynamicType> queryDynamicTypesByPage(String key, Integer page, Integer rows, String sortBy[], Boolean desc[], Boolean isAll) {
        //初始化example对象
        Example example = new Example(DynamicType.class);
        Example.Criteria criteria = example.createCriteria();
        if (isAll){
            criteria.andNotEqualTo("typeStatus", "-1");
        }else {
            criteria.andEqualTo("typeStatus", "-1");
        }
        //根据name模糊查询
        if (StringUtils.isNotBlank(key)) {
            // System.out.println("key::::::::::"+key);
            criteria.andLike("typeName", "%" + key + "%");
        }
        //添加分页条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (ArrayUtils.isNotEmpty(sortBy)) {
            example.setOrderByClause(sortBy[0] + " " + (desc[0] ? "desc" : "asc"));
        }
        List<DynamicType> dynamicTypes = this.typeMapper.selectByExample(example);
        PageInfo<DynamicType> dynamicTypePageInfo = new PageInfo<>(dynamicTypes);

        return new PageResult<>(dynamicTypePageInfo.getTotal(), dynamicTypePageInfo.getList());
    }

    /**
     * 新增分类
     * @param dynamicType
     */
    @Override
    @Transactional
    public void addType(DynamicType dynamicType) {
        System.out.println("service::::"+dynamicType);
        this.typeMapper.insertSelective(dynamicType);
    }

    /**
     * 根据id更新分类
     *
     * @param dynamicType
     */
    @Override
    @Transactional
    public void updateType(DynamicType dynamicType) {
        Example example = new Example(DynamicType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",dynamicType.getId());
        this.typeMapper.updateByExampleSelective(dynamicType,example);
    }

    /**
     * 删除类型
     *
     * @param dynamicType
     */
    @Override
    @Transactional
    public void deleteType(DynamicType dynamicType) {
        Example example = new Example(DynamicType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",dynamicType.getId());
        this.typeMapper.deleteByExample(example);
    }

    /**
     * 查询所有分类名
     *
     * @return
     */
    @Override
    public List<String> queryAllDynamiTypeNames() {

        List<String> names = this.typeMapper.queryAllDynamiTypeNames();
        return names;
    }

    /**
     * 根据id查询分类名
     *
     * @param id
     * @return
     */
    @Override
    public String queryNameById(Integer id) {
        Example example = new Example(DynamicType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",id).andEqualTo("typeStatus","1");
        DynamicType dynamicType = this.typeMapper.selectOneByExample(example);
        if (dynamicType == null){
            return null;
        }else {
            return dynamicType.getTypeName();
        }

    }

}
