package com.dxg.msns.search.pojo;

public class SearchRequst {
    private String key;
    private Integer page;
    private String sortBy;
    private Boolean desc; //是否降序
    private static final Integer DEFAULT_SIZE = 1;
    private static final Integer DEFAULT_PAGE = 1;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        if(page == null){
            return DEFAULT_PAGE;
        }
        // 获取页码时做一些校验，不能小于1
        return Math.max(DEFAULT_PAGE, page);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Boolean getDesc() {
        return desc;
    }

    public void setDesc(Boolean desc) {
        this.desc = desc;
    }

    public Integer getSize(){
        return DEFAULT_SIZE;
    }
}
