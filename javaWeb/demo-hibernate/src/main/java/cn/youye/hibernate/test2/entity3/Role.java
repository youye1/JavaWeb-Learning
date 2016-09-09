package cn.youye.hibernate.test2.entity3;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 单向多对多映射：一个用户有多种角色，一种角色对应多个用户
 * Created by pc on 2016/9/2.
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    private String id;
    @Column
    private String name;

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
}
