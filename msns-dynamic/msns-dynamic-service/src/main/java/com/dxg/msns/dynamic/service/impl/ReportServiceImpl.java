package com.dxg.msns.dynamic.service.impl;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.common.util.UnderlineHump;
import com.dxg.msns.dynamic.mapper.DynamicMapper;
import com.dxg.msns.dynamic.pojo.Dynamic;
import com.dxg.msns.dynamic.pojo.DynamicType;
import com.dxg.msns.dynamic.service.ReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private DynamicMapper dynamicMapper;
    @Override
    public PageResult<Dynamic> queryReportsByPage(String key, Integer page, Integer rows, String[] sortBy, Boolean[] desc, Boolean isAll) {
        //添加分页条件
        PageHelper.startPage(page, rows);
        String isDesc = "desc";
        key = "%"+key+"%";

        //添加排序条件
        if (ArrayUtils.isNotEmpty(sortBy)) {
            isDesc = desc[0] ? "desc" : "asc";
        }
        List<Dynamic> dynamics = this.dynamicMapper.selectAllReports(key,UnderlineHump.HumpToUnderline(sortBy[0]),isDesc);
        for (int i = 0; i < dynamics.size(); i++) {
            if (!dynamics.get(i).getReports().isEmpty()){
                dynamicMapper.insertReportCount(dynamics.get(i).getDynamicId(),dynamics.get(i).getReports().size());
            }
        }
        PageInfo<Dynamic> dynamicPageInfo = new PageInfo<>(dynamics);
        return new PageResult<>(dynamicPageInfo.getTotal(), dynamicPageInfo.getList());
    }
}
