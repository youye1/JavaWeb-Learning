package cn.youye.hibernate.test2.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置String类型的实体
 * 由于Java注解需要配置到实体类中，因此JPA要求实体类必须为POJO类型，而不能为String等基本类型。而XML配置则允许将String基本类型作为实体类。因此使用XML配置，无需封装Email.
 * Created by pc on 2016/9/9.
 */
public class Person2 {

    private String id;
    private String name;
    private List<String> emails=new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
