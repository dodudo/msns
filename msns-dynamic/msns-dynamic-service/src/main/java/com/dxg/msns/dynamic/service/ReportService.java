package com.dxg.msns.dynamic.service;

import com.dxg.msns.common.pojo.PageResult;
import com.dxg.msns.dynamic.pojo.Dynamic;

public interface ReportService {
    public PageResult<Dynamic> queryReportsByPage(String key, Integer page, Integer rows, String[] sortBy, Boolean[] desc, Boolean isAll);
}
