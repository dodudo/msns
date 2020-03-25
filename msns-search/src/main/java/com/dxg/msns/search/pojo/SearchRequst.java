package com.dxg.msns.search.pojo;

import java.util.Arrays;

public class SearchRequst {
    private String key;
    private Integer page;
    private String sortBy;
    private Boolean desc; //是否降序
    private String[] uids; //查询用户id
    private Integer[] ids;
    private Integer typeId;
    private static final Integer DEFAULT_SIZE = 1;
    private static final Integer DEFAULT_PAGE = 1;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public String[] getUids() {
        return uids;
    }

    public void setUids(String[] uids) {
        this.uids = uids;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "SearchRequst{" +
                "key='" + key + '\'' +
                ", page=" + page +
                ", sortBy='" + sortBy + '\'' +
                ", desc=" + desc +
                ", uids=" + Arrays.toString(uids) +
                ", ids=" + Arrays.toString(ids) +
                ", typeId=" + typeId +
                '}';
    }
}
