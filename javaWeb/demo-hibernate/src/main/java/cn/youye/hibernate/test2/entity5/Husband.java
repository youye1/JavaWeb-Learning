package cn.youye.hibernate.test2.entity5;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 一对一关系测试类
 * Created by pc on 2016/9/10.
 */
@Entity
@Table(name = "husband")
public class Husband {
    @Id
    private String id;
    private String name;

    //添加Wife引用以及一对一配置，由单向一对一关联变为双向一对一
//    @OneToOne(mappedBy = "wife")
//    private Wife wife;

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

//    public Wife getWife() {
//        return wife;
//    }
//
//    public void setWife(Wife wife) {
//        this.wife = wife;
//    }
}
