package com.dxg.msns.search.pojo;

import com.dxg.msns.music.pojo.Music;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "dynamics",type = "docs",shards = 1,replicas = 0)
public class Dynamics {
    @Id
    private Integer id;  //动态id
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String all;  //所有的查询条件
    private Integer typeId; //分类id
    private Date publishDate; //发布时间
    private Date recentCommentDate; //最新评论时间
    @Field(type = FieldType.Text,analyzer = "ik_max_word",index = false)
    private String dynamicContent;
    private Long viewCount; //浏览次数
    private Integer likeCount; //点赞次数
    private Integer favorCount; //收藏次数
    private Integer commentCount; //评论次数
    private String imgUrls; //图片
    private String author; //发布者
    private String authorAvatar; //发布者头像
    private Music music;
    @Field(type = FieldType.Keyword)
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getRecentCommentDate() {
        return recentCommentDate;
    }

    public void setRecentCommentDate(Date recentCommentDate) {
        this.recentCommentDate = recentCommentDate;
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Integer favorCount) {
        this.favorCount = favorCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Dynamics{" +
                "id=" + id +
                ", all='" + all + '\'' +
                ", typeId=" + typeId +
                ", publishDate=" + publishDate +
                ", recentCommentDate=" + recentCommentDate +
                ", dynamicContent='" + dynamicContent + '\'' +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", favorCount=" + favorCount +
                ", commentCount=" + commentCount +
                ", imgUrls='" + imgUrls + '\'' +
                ", author='" + author + '\'' +
                ", authorAvatar='" + authorAvatar + '\'' +
                ", music=" + music +
                ", status='" + status + '\'' +
                '}';
    }
}
