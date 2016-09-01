package cn.youye.hibernate.test1.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Blob;
import java.util.Date;

/**
 * 实体类
 * Created by pc on 2016/9/1.
 */
public class Student {

    private int id;
    private String name;//姓名
    private String gender;//性别
    @JSONField(format = "yyyy/MM/dd")
    private Date birthday;//生日
    private String address;
    private Blob picture;

    public Student() {
    }

    public Student(int id, String name, String gender, Date birthday, String address) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }
}
