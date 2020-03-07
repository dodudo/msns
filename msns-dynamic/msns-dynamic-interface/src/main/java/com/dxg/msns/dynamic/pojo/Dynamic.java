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
    private Integer likeCount;
    private Integer favor;
    private Integer dynamicTypeId;
    private Date publishDate;
    private String musicId;
    private Integer view;
    private String status;
    private List<Report> reports;
    private Long reportCount;

    public Long getReportCount() {
        return reportCount;
    }

    public void setReportCount(Long reportCount) {
        this.reportCount = reportCount;
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



    public Integer getFavor() {
        return favor;
    }

    public void setFavor(Integer favor) {
        this.favor = favor;
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

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
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
                ", likeCount=" + likeCount +
                ", favor=" + favor +
                ", dynamicTypeId=" + dynamicTypeId +
                ", publishDate=" + publishDate +
                ", musicId='" + musicId + '\'' +
                ", view=" + view +
                ", status='" + status + '\'' +
                ", reports=" + reports +
                ", reportCount=" + reportCount +
                '}';
    }
}
