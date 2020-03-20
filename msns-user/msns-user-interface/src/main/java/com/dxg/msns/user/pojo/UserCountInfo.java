package com.dxg.msns.user.pojo;

public class UserCountInfo {
    private Integer favorCount; //关注数
    private Integer fanCount; //粉丝数
    private Integer dynamicCount; //动态数
    private Integer collectDynamicCount; //收藏动态数
    private Integer collectMusicCount; //收藏音乐数

    public Integer getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Integer favorCount) {
        this.favorCount = favorCount;
    }

    public Integer getFanCount() {
        return fanCount;
    }

    public void setFanCount(Integer fanCount) {
        this.fanCount = fanCount;
    }

    public Integer getDynamicCount() {
        return dynamicCount;
    }

    public void setDynamicCount(Integer dynamicCount) {
        this.dynamicCount = dynamicCount;
    }

    public Integer getCollectDynamicCount() {
        return collectDynamicCount;
    }

    public void setCollectDynamicCount(Integer collectDynamicCount) {
        this.collectDynamicCount = collectDynamicCount;
    }

    public Integer getCollectMusicCount() {
        return collectMusicCount;
    }

    public void setCollectMusicCount(Integer collectMusicCount) {
        this.collectMusicCount = collectMusicCount;
    }

    @Override
    public String toString() {
        return "UserCountInfo{" +
                "favorCount=" + favorCount +
                ", fanCount=" + fanCount +
                ", dynamicCount=" + dynamicCount +
                ", collectDynamicCount=" + collectDynamicCount +
                ", collectMusicCount=" + collectMusicCount +
                '}';
    }
}
