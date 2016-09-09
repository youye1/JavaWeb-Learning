package cn.youye.hibernate.test2.entity2;

import javax.persistence.*;

/**
 * 单向多对一关联映射测试实体类
 * Article:为多方
 * type:为一方
 * Created by pc on 2016/9/9.
 */
@Entity
@Table(name="article")
public class Article {
    @Id
    private String id;
    private String name;

    @Column(columnDefinition = "text")
    private String content;

    @ManyToOne(cascade = {CascadeType.PERSIST},
    fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
