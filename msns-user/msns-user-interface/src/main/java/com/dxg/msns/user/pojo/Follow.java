package com.dxg.msns.user.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "follow")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String followersId;
    private String befollowersId;
    private Date followerDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFollowersId() {
        return followersId;
    }

    public void setFollowersId(String followersId) {
        this.followersId = followersId;
    }

    public String getBefollowersId() {
        return befollowersId;
    }

    public void setBefollowersId(String befollowersId) {
        this.befollowersId = befollowersId;
    }

    public Date getFollowerDate() {
        return followerDate;
    }

    public void setFollowerDate(Date followerDate) {
        this.followerDate = followerDate;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", followersId='" + followersId + '\'' +
                ", befollowersId='" + befollowersId + '\'' +
                ", followerDate=" + followerDate +
                '}';
    }
}
