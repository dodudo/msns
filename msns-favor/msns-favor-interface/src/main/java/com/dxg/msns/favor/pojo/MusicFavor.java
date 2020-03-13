package com.dxg.msns.favor.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * (MusicFavor)实体类
 *
 * @author makejava
 * @since 2020-03-13 20:44:45
 */
@Table(name = "music_favor")
public class MusicFavor implements Serializable {
    private static final long serialVersionUID = -37616566427231476L;
    /**
    * 主键自增
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
    * 音乐id
    */
    private String musicId;
    /**
    * 收藏者id
    */
    private String collectorId;
    /**
    * 收藏时间
    */
    private Date favorDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
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

    @Override
    public String toString() {
        return "MusicFavor{" +
                "id=" + id +
                ", musicId='" + musicId + '\'' +
                ", collectorId='" + collectorId + '\'' +
                ", favorDate=" + favorDate +
                '}';
    }
}