package cn.youye.hibernate.test2.entity3;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 单向多对多映射：一个用户有多种角色，一种角色对应多个用户
 * Created by pc on 2016/9/2.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;

    private String name;

    @Column(length = 4)
    private String sex;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST})
    @JoinTable( name = "user_role",
    joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private List<Role> roles=new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
