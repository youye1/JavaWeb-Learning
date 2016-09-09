package cn.youye.hibernate.test2.entity;

import javax.persistence.*;

/**
 * 邮箱实体类
 * 测试一对多映射关系：一个人有多个邮箱
 * Email:为多方
 * Person：为一方
 * Created by pc on 2016/9/8.
 */
@Entity
@Table(name = "email")
public class Email {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String email;

    public Email() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
