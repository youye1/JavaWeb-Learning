package cn.youye.hibernate.test2.entity4;

import javax.persistence.*;

/**
 * Created by pc on 2016/9/10.
 */
@Entity
@Table(name = "student1")
public class Student1 {
    @Id
    private String id;
    private String name;
    private String sex;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
