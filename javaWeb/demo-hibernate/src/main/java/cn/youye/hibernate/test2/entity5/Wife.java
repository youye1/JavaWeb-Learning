package cn.youye.hibernate.test2.entity5;

import javax.persistence.*;

/**
 * 一对一关系测试类
 * Created by pc on 2016/9/10.
 */
@Entity
@Table(name = "wife")
public class Wife {
    @Id
    private String id;
    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "husband_id",unique = true)
    private Husband husband;

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

    public Husband getHusband() {
        return husband;
    }

    public void setHusband(Husband husband) {
        this.husband = husband;
    }
}
