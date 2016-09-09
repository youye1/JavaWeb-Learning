package cn.youye.hibernate.test2.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 个人实体类
 * 测试一对多映射关系：一个人有多个邮箱
 * Email:为多方
 * Person：为一方
 * Created by pc on 2016/9/8.
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY,targetEntity = Email.class,
    cascade = {
            CascadeType.PERSIST,CascadeType.REMOVE,
            CascadeType.MERGE,CascadeType.REFRESH
    })               //一对多关系配置
    @JoinColumns(value = {@JoinColumn(name = "person_id",referencedColumnName = "id")})
    @OrderBy(value = "email desc ")
    private List<Email> emails=new ArrayList<Email>();

    public Person() {
    }

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

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
}
