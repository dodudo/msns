package com.dxg.msns.search.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import java.util.Date;

@Document(indexName = "musics",type = "docs",shards = 1,replicas = 0)
public class Musics {
    @Id
    private Integer id;
    @Field(type = FieldType.Keyword)
    private String musicId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String all;  //所有的查询条件
    @Field(type = FieldType.Keyword,index = false)
    private String musicCoverUrl;
    @Field(type = FieldType.Keyword)
    private String musicName;
    @Field(type = FieldType.Keyword)
    private String musicAuthor;
    @Field(type = FieldType.Keyword)
    private String musicAlbum;
    @Field(type = FieldType.Keyword)
    private String musicPublisDate;
    private Date musicPushDate;
    @Field(type = FieldType.Keyword,index = false)
    private String musicLength;
    private Integer musicTypeId;
    @Field(type = FieldType.Keyword,index = false)
    private String musicUrl;
    @Field(type = FieldType.Keyword)
    private String status;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

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
        return "Musics{" +
                "id=" + id +
                ", musicId='" + musicId + '\'' +
                ", all='" + all + '\'' +
                ", musicCoverUrl='" + musicCoverUrl + '\'' +
                ", musicName='" + musicName + '\'' +
                ", musicAuthor='" + musicAuthor + '\'' +
                ", musicAlbum='" + musicAlbum + '\'' +
                ", musicPublisDate='" + musicPublisDate + '\'' +
                ", musicPushDate=" + musicPushDate +
                ", musicLength='" + musicLength + '\'' +
                ", musicTypeId=" + musicTypeId +
                ", musicUrl='" + musicUrl + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
