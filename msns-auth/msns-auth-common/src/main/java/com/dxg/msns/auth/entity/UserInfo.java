package com.dxg.msns.auth.entity;

public class UserInfo {
    private Integer id;
    private String uid;
    private String uname;

    public UserInfo() {
    }

    public UserInfo(Integer id, String uname) {
        this.id = id;
        this.uname = uname;
    }

    public UserInfo(Integer id, String uid, String uname) {
        this.id = id;
        this.uid = uid;
        this.uname = uname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
