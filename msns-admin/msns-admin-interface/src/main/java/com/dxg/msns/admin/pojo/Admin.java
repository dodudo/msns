package com.dxg.msns.admin.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2020-03-10 10:53:49
 */
@Table(name = "admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = -24290352760927882L;
    /**
    * 主键id自增
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
    * 管理员id
    */
    private String adminid;
    /**
    * 管理员昵称
    */
    private String aname;
    /**
    * 管理员性别
    */
    private String sex;
    /**
    * 管理员生日
    */
    private Date birthday;
    /**
    * 管理员手机
    */
    private String phone;
    /**
    * 管理员邮箱
    */
    private String email;
    /**
    * 管理员密码
    */
    private String apassword;
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

    public String getAdminid() {
        return adminid;
    }

    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminid='" + adminid + '\'' +
                ", aname='" + aname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", apassword='" + apassword + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}