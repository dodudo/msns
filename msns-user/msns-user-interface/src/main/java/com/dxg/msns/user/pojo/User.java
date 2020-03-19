package com.dxg.msns.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uid;
    @Pattern(regexp = "^[a-zA-Z]{4,10}$", message = "用户名只能在4~10位之间")
    private String uname;
//    @Pattern(regexp = "^1[35678]\\d{9}$", message = "手机号格式不正确")
    private String email;
    @Pattern(regexp = "^\\w+@[a-z0-9]+\\.[a-z]{2,4}$", message = "手机号格式不正确")
    private String phone;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //json序列化时忽略,不把密码传回去
    @Length(min = 6, max = 20, message = "密码只能在6~20位之间")
    private String upassword;
    private String sex;
    private Date birthday;
    private String avatarUrl;
    private String description;
    private String status;
    @JsonIgnore //json序列化时忽略,不传回去
    private String salt; //密码的盐值

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", upassword='" + upassword + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
