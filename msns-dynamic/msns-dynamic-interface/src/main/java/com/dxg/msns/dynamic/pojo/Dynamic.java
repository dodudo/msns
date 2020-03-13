package com.dxg.msns.dynamic.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Table(name = "dynamic")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Dynamic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String dynamicId;
    private String uid;
    private String uname;
    private String avatarUrl;
    private String dynamicContent;
    private String imgUrls;
    private Integer dynamicTypeId;
    private Date publishDate;
    private String musicId;
    private String status;
    private List<Report> reports;
    private Long reportCount;
    private Long viewCount; //浏览次数
    private Long likeCount; //点赞次数
    private Long favorCount; //收藏次数
    private Long commentCount; //评论次数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Integer getDynamicTypeId() {
        return dynamicTypeId;
    }

    public void setDynamicTypeId(Integer dynamicTypeId) {
        this.dynamicTypeId = dynamicTypeId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public Long getReportCount() {
        return reportCount;
    }

    public void setReportCount(Long reportCount) {
        this.reportCount = reportCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Long favorCount) {
        this.favorCount = favorCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "Dynamic{" +
                "id=" + id +
                ", dynamicId='" + dynamicId + '\'' +
                ", uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", dynamicContent='" + dynamicContent + '\'' +
                ", imgUrls='" + imgUrls + '\'' +
                ", dynamicTypeId=" + dynamicTypeId +
                ", publishDate=" + publishDate +
                ", musicId='" + musicId + '\'' +
                ", status='" + status + '\'' +
                ", reports=" + reports +
                ", reportCount=" + reportCount +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", favorCount=" + favorCount +
                ", commentCount=" + commentCount +
                '}';
    }
}
