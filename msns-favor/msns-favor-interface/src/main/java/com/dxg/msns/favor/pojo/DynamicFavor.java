package com.dxg.msns.favor.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * (DynamicFavor)实体类
 *
 * @author makejava
 * @since 2020-03-13 20:43:30
 */
@Table(name = "dynamic_favor")
public class DynamicFavor implements Serializable {
    private static final long serialVersionUID = 889485348889726487L;
    /**
    * 自增主键id
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
    * 动态id
    */
    private Integer dynamicId;
    /**
    * 收藏者id
    */
    private String collectorId;
    /**
    * 收藏时间
    */
    private Date favorDate;
    /**
    * 状态
    */
    private String status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

    public Date getFavorDate() {
        return favorDate;
    }

    public void setFavorDate(Date favorDate) {
        this.favorDate = favorDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DynamicFavor{" +
                "id=" + id +
                ", dynamicId='" + dynamicId + '\'' +
                ", collectorId='" + collectorId + '\'' +
                ", favorDate=" + favorDate +
                ", status='" + status + '\'' +
                '}';
    }
}