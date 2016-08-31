package cn.youye.spring.spelCheck;

/**
 * Created by pc on 2016/8/17.
 */
public class ProvinceObj {
    private String ID;
    private String name;

    public ProvinceObj() {
    }

    public ProvinceObj(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
