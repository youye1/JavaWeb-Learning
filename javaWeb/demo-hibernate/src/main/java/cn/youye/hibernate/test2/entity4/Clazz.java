package cn.youye.hibernate.test2.entity4;

import cn.youye.hibernate.test1.entity.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试双边一对多和多对一关系
 * Created by pc on 2016/9/10.
 */
@Entity
@Table(name = "clazz")
public class Clazz {
    @Id
    private String id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "clazz")
    private List<Student1> student1s = new ArrayList<>();

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

    public List<Student1> getStudent1s() {
        return student1s;
    }

    public void setStudent1s(List<Student1> student1s) {
        this.student1s = student1s;
    }
}
