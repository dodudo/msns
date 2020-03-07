package com.dxg.msns.dynamic.service.impl;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.common.util.UnderlineHump;
import com.dxg.msns.dynamic.mapper.DynamicMapper;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.dynamic.pojo.DynamicType;
import com.dxg.msns.dynamic.service.DynamicService;
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
public class DynamicServiceImpl implements DynamicService {
    @Autowired
    private DynamicMapper dynamicMapper;
    /**
     * 查询所有动态
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
    public PageResult<Dynamic> queryDynamicsByPage(String key, Integer page, Integer rows, String[] sortBy, Boolean[] desc, Boolean isAll) {
        Example example = new Example(Dynamic.class);
        Example.Criteria criteria = example.createCriteria();
        if (isAll){
            criteria.andNotEqualTo("status","-1");
        }else {
            criteria.andEqualTo("status","-1");
        }
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("dynamicContent","%"+key+"%");
        }
        //添加分页条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (ArrayUtils.isNotEmpty(sortBy)) {

            example.setOrderByClause( UnderlineHump.HumpToUnderline(sortBy[0]) + " " + (desc[0] ? "desc" : "asc"));
        }
        List<Dynamic> dynamics = this.dynamicMapper.selectByExample(example);
        PageInfo<Dynamic> dynamicPageInfo = new PageInfo<>(dynamics);

        return new PageResult<>(dynamicPageInfo.getTotal(), dynamicPageInfo.getList());
    }

    /**
     * 更新动态
     *
     * @param dynamic
     */
    @Override
    @Transactional
    public void updateDynamic(Dynamic dynamic) {
        Example example = new Example(Dynamic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicId",dynamic.getDynamicId());
        this.dynamicMapper.updateByExampleSelective(dynamic,example);
    }

    /**
     * 删除动态
     *
     * @param dynamic
     */
    @Override
    @Transactional
    public void deleteDynamic(Dynamic dynamic) {
        Example example = new Example(Dynamic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dynamicId",dynamic.getDynamicId());
        this.dynamicMapper.deleteByExample(example);
    }
}
