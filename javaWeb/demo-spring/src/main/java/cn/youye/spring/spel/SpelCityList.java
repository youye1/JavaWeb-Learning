package cn.youye.spring.spel;

import java.util.List;

/**
 * 集合操作类--包含操作结果的类
 * Created by pc on 2016/8/17.
 */
//2.定义操作结果类
public class SpelCityList {

    private SpelCity choseCity1;
    private SpelCity choseCity2;
    private SpelCity aBigCity1;
    private SpelCity aBigCity2;

    private List bigCitys;
    private List cityName1;
    private List cityName2;
    private List cityName3;

    /**
     * getter 和 setter方法
     * @return
     */

    public SpelCity getChoseCity1() {
        return choseCity1;
    }

    public void setChoseCity1(SpelCity choseCity1) {
        this.choseCity1 = choseCity1;
    }

    public SpelCity getChoseCity2() {
        return choseCity2;
    }

    public void setChoseCity2(SpelCity choseCity2) {
        this.choseCity2 = choseCity2;
    }

    public List getBigCitys() {
        return bigCitys;
    }

    public void setBigCitys(List bigCitys) {
        this.bigCitys = bigCitys;
    }

    public SpelCity getaBigCity1() {
        return aBigCity1;
    }

    public void setaBigCity1(SpelCity aBigCity1) {
        this.aBigCity1 = aBigCity1;
    }

    public SpelCity getaBigCity2() {
        return aBigCity2;
    }

    public void setaBigCity2(SpelCity aBigCity2) {
        this.aBigCity2 = aBigCity2;
    }

    public List getCityName1() {
        return cityName1;
    }

    public void setCityName1(List cityName1) {
        this.cityName1 = cityName1;
    }

    public List getCityName2() {
        return cityName2;
    }

    public void setCityName2(List cityName2) {
        this.cityName2 = cityName2;
    }

    public List getCityName3() {
        return cityName3;
    }

    public void setCityName3(List cityName3) {
        this.cityName3 = cityName3;
    }
}
