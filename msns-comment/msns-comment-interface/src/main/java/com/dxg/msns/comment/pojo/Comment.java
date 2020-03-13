package com.dxg.msns.comment.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;

/**
 * (Comment)实体类
 *
 * @author makejava
 * @since 2020-03-13 20:13:00
 */
@Table(name = "comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = -15178278581914246L;
    /**
    * 评论id
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
    * 父id
    */
    private Long fid;
    /**
    * 动态id
    */
    private String dynamicId;
    /**
    * 动态发布者
    */
    private String name;
    /**
    * 回复者id
    */
    private String replyId;
    /**
    * 被评论者id
    */
    private String respondentId;
    /**
    * 评论日期
    */
    private Date commentDate;
    /**
    * 评论内容
    */
    private String commentContent;
    /**
    * 状态
    */
    private String status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    public String getRespondentId() {
        return respondentId;
    }

    public void setRespondentId(String respondentId) {
        this.respondentId = respondentId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", fid=" + fid +
                ", dynamicId='" + dynamicId + '\'' +
                ", name='" + name + '\'' +
                ", replyId='" + replyId + '\'' +
                ", respondentId='" + respondentId + '\'' +
                ", commentDate=" + commentDate +
                ", commentContent='" + commentContent + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}