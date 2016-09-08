package cn.youye.hibernate.test2.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 单向一对多映射：一个用户有多种角色
 * role为多方
 * user为一方
 * Created by pc on 2016/9/2.
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;

    //定义多方的集合
    @OneToMany(fetch = FetchType.EAGER,targetEntity = User.class,
    cascade = {
//            配置级联关系
            CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH
    })

    @JoinColumns(value = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    @OrderBy(value = "id desc ")
    private List<User> users = new ArrayList<User>();

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
