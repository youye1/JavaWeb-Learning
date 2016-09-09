package cn.youye.hibernate.test2.entity2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 单向多对一关联映射测试实体类
 * Article:为多方
 * type:为一方
 * Created by pc on 2016/9/9.
 */
@Entity
@Table(name="type")
public class Type {
    @Id
    private String id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
