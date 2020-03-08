package com.dxg.msns.music.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String musicId;

    private String musicCoverUrl;
    private String musicName;
    private String musicAuthor;
    private String musicAlbum;
    private String musicPublisDate;
    private Date musicPushDate;
    private String musicLength;
    private Integer musicTypeId;
    private String musicUrl;
    private String status;

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

    public String getMusicCoverUrl() {
        return musicCoverUrl;
    }

    public void setMusicCoverUrl(String musicCoverUrl) {
        this.musicCoverUrl = musicCoverUrl;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum;
    }

    public String getMusicPublisDate() {
        return musicPublisDate;
    }

    public void setMusicPublisDate(String musicPublisDate) {
        this.musicPublisDate = musicPublisDate;
    }

    public Date getMusicPushDate() {
        return musicPushDate;
    }

    public void setMusicPushDate(Date musicPushDate) {
        this.musicPushDate = musicPushDate;
    }

    public String getMusicLength() {
        return musicLength;
    }

    public void setMusicLength(String musicLength) {
        this.musicLength = musicLength;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public Integer getMusicTypeId() {
        return musicTypeId;
    }

    public void setMusicTypeId(Integer musicTypeId) {
        this.musicTypeId = musicTypeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", musicId='" + musicId + '\'' +
                ", musicCoverUrl='" + musicCoverUrl + '\'' +
                ", musicName='" + musicName + '\'' +
                ", musicAuthor='" + musicAuthor + '\'' +
                ", musicAlbum='" + musicAlbum + '\'' +
                ", musicPublisDate=" + musicPublisDate +
                ", musicPushDate=" + musicPushDate +
                ", musicLength='" + musicLength + '\'' +
                ", musicTypeId=" + musicTypeId +
                ", musicUrl='" + musicUrl + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
