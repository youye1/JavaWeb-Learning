package cn.youye.hibernate.util;

import cn.youye.hibernate.testWeb.entity.Department;
import cn.youye.hibernate.testWeb.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;
import java.util.Random;

/**
 * 返回一个随机的Employee数据。
 * Created by pc on 2016/9/10.
 */
public class StringUtil {
    private static Random random = new Random(); //随机数生成器

    public static Employee getRandomEmployee() {
        String name = "";
        //随机生成姓名
        for (int i = 0, n = 2 + (random.nextInt(8) == 0 ? 0 : 1); i < n; i++) {
            try {
                name += getChineseCharacter(System.currentTimeMillis() + i);
            } catch (Exception e) {

            }
        }
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(20 + random.nextInt(40));

        int year = 1950 + random.nextInt(40);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(31);

        employee.setBirthday(Date.valueOf(year + "-" + month + "-" + day));
        employee.setDescription("这是一个随机的Employee");
        employee.setDisabled(random.nextInt(10) == 9);
        employee.setSex(random.nextInt(3) == 2 ? "男" : "女");

        int hh = 7 + random.nextInt(2);
        int mm = random.nextInt(60);
        int ss = random.nextInt(60);
        employee.setStartTime(Time.valueOf(hh + ":" + mm + ":" + ss));

        hh = 16 + random.nextInt(2);
        employee.setEndTime(Time.valueOf(hh + ":" + mm + ":" + ss));

        double salary = 1000 + random.nextDouble() * 5000;
        employee.setSalary(salary);

        return employee;
    }

    /**
     * 生成汉字
     *
     * @param seed
     * @return
     */
    public static String getChineseCharacter(long seed) throws UnsupportedEncodingException {
        String str = null;            //保存结果
        int highPos, lowPos;         //高位、地位
        Random random = new Random(seed); //随机数生成器
        highPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = 161 + Math.abs(random.nextInt(93));
        byte[] b = new byte[2];
        b[0] = (new Integer(highPos)).byteValue();
        b[1] = (new Integer(lowPos)).byteValue();
        str = new String(b, "GBK");
        return str;
    }

    /**
     * 判断是否为空
     */
    public static boolean isNull(Object object) {
        if (object == null || object == "") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 计算URL
     */
    public static String getURL(HttpServletRequest request) {

        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        String url = requestURI + "?" + filterQueryString(queryString);

        if (!url.endsWith("?")) {
            url += "&";
        }
        return url;
    }

    /**
     * 过滤URL
     *
     * @param queryString
     * @return
     */
    public static String filterQueryString(String queryString) {
        if (queryString == null) {
            return "";
        }
        queryString = queryString.replaceAll("[^&]*sort=[^&]*", "");   //过滤sort
        queryString = queryString.replaceAll("[^&]*order=[^&]*", "");  //过滤order
        queryString = queryString.replaceAll("[^&]*action=[^&]*", "");  //过滤action
        queryString = queryString.replaceAll("${2,}", "&");    //过滤重复的&
        queryString = queryString.replaceAll("^&", "");    //过滤开始字符
        queryString = queryString.replaceAll("&$", "");    //过滤结束字符
        return queryString;

    }
}
