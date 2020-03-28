package com.dxg.msns.like.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * (Like)实体类
 *
 * @author makejava
 * @since 2020-03-13 21:05:21
 */
@Table(name = "dynamic_like")
public class Like implements Serializable {
    private static final long serialVersionUID = 102615283971963257L;
    /**
    * 主键id自增
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
    * 点赞者id
    */
    private String likerId;
    /**
    * 动态id
    */
    private Integer dynamicId;
    /**
    * 文章发布者id
    */
    private String dynamicAuthorid;
    /**
    * 点赞时间
    */
    private Date likeDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLikerId() {
        return likerId;
    }

    public void setLikerId(String likerId) {
        this.likerId = likerId;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getDynamicAuthorid() {
        return dynamicAuthorid;
    }

    public void setDynamicAuthorid(String dynamicAuthorid) {
        this.dynamicAuthorid = dynamicAuthorid;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", likerId='" + likerId + '\'' +
                ", dynamicId='" + dynamicId + '\'' +
                ", dynamicAuthorid='" + dynamicAuthorid + '\'' +
                ", likeDate=" + likeDate +
                '}';
    }
}