package cn.youye.hibernate.testWeb.servlet;

import cn.youye.hibernate.testWeb.entity.Employee;
import cn.youye.hibernate.util.HibernateUtil;
import cn.youye.hibernate.util.StringUtil;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

/**
 * Created by pc on 2016/9/10.
 */
public class EmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if ("add".equals(action)) {
            add(req, resp);
            return;
        } else if ("form".equals(action)) {
            form(req, resp);
        } else if ("delete".equals(action)) {
            delete(req, resp);
        }
        listEmployee(req, resp);
    }

    /**
     * 列表页面
     *
     * @param req
     * @param resp
     */
    private void listEmployee(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

        String sort = req.getParameter("sort");
        String order = req.getParameter("order");
        if (StringUtil.isNull(sort)) {
            sort = "id";  //默认为id排序
        }
        if (StringUtil.isNull(order)) {
            order = "desc";   //默认为desc降序排序
        }

        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        String ageOperate = req.getParameter("ageOperate");   //操作，> < =
        String birthday = req.getParameter("birthday");
        String time = req.getParameter("time");   //需要查询的时间
        String salary = req.getParameter("salary");
        String salaryOperate = req.getParameter("salaryOperate"); //操作 > < =
        String description = req.getParameter("description");
        String disabled = req.getParameter("disabled");
        String departmentName = req.getParameter("departmentName");//部门名称

        String where = "";    //条件子句，组织查询条件

        if (!StringUtil.isNull(name)) {
            if (!StringUtil.isNull(where)) {
                where += " and ";
            }
            where += " e.name like '%" + name + "%' ";
        }
        if (!StringUtil.isNull(sex)) {
            if (!StringUtil.isNull(where)) {
                where += " and ";
            }
            where += " e.sex = '" + sex + "'";
        }
        if (!StringUtil.isNull(age)) {
            if (!StringUtil.isNull(where)) {
                where += " and ";
            }
            where += " e.age " + ageOperate + " " + age;
        }
        if (!StringUtil.isNull(birthday)) {
            if (!StringUtil.isNull(where)) {
                where += " and ";
            }
            where += " e.birthday ='" + birthday + "'";
        }
        if (!StringUtil.isNull(time)) {
            if (!StringUtil.isNull(where)) {
                where += " and ";
            }
            where += "(e.startTime <= '" + time + "' and e.endTime>='" + time + "' )";
        }
        if (!StringUtil.isNull(salary)) {
            if (!StringUtil.isNull(where)) {
                where += " and ";
            }
            where += " e.salary " + salaryOperate + " " + salary;
        }
        if (!StringUtil.isNull(description)) {
            if (!StringUtil.isNull(where)) {
                where += " and ";
            }
            where += " e.description like '%" + description + "%' ";
        }
        if (!StringUtil.isNull(disabled)) {
            if (!StringUtil.isNull(where)) {
                where += " and ";
            }
            where += "e.disabled = " + disabled;
        }
        if (!StringUtil.isNull(departmentName)) {
            if (!StringUtil.isNull(where)) {
                where += " and ";
            }
            where += " e.department.name like '%" + departmentName + "%' ";
        }
        String hql = "from Employee e";   //主HQL语句
        if (!StringUtil.isNull(where)) {
            hql += " where " + where;       //拼接条件查询
        }
        hql += " order by e." + sort + " " + order;  //追加排序

        //创建session
        Session session = HibernateUtil.getSessionFactory().openSession();

        //查询总记录数
        Number result = (Number) session.createQuery("select count(e) " + hql).uniqueResult();
        int count = result.intValue();    //获取总记录条数

//        Pagination pagination = new Pagination(); //分页器

        //查询员工列表
        List<Employee> list = session.createQuery("select e " + hql).list();

        req.setAttribute("list", list);
        req.setAttribute("order", order);
        req.setAttribute("sort", sort);
        req.setAttribute("url", StringUtil.getURL(req));
        if (req.getAttribute("message") == null) {
            req.setAttribute("message", "HQL: " + hql);
        }
        try {
            req.getRequestDispatcher("/WEB-INF/views/testWeb/employee/list.jsp").forward(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;

    }

    /**
     * 表单页面
     *
     * @param req
     * @param resp
     */
    private void form(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();
        String id = req.getParameter("id");
        if (!StringUtil.isNull(id)) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            employee = session.get(Employee.class, id);
            if (!StringUtil.isNull(employee.getDepartment())) {
                req.setAttribute("departmentId", employee.getDepartment().getId());
            }
        }
        req.setAttribute("id", employee.getId());
        req.setAttribute("name", employee.getName());
        req.setAttribute("sex", employee.getSex());
        req.setAttribute("age", employee.getAge());
        req.setAttribute("birthday", employee.getBirthday());
        req.setAttribute("startTime", employee.getStartTime());
        req.setAttribute("endTime", employee.getEndTime());
        req.setAttribute("salary", employee.getSalary());

        req.getRequestDispatcher("/WEB-INF/views/testWeb/employee/form.jsp").forward(req, resp);
        return;
    }

    /**
     * 添加页面
     *
     * @param req
     * @param resp
     */
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
//
//        Employee employee = StringUtil.getRandomEmployee();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee employee = new Employee();
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        int age = Integer.getInteger(req.getParameter("age"));
        String birthday = req.getParameter("birthday");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        double salary = Double.parseDouble(req.getParameter("salary"));
        String departmentId = req.getParameter("departmentId");

        if (!StringUtil.isNull(id)) {
            employee = session.get(Employee.class, id);
        }
        employee.setName(name);
        employee.setSex(sex);
        employee.setAge(age);
        employee.setSalary(salary);
//        employee.setStartTime(startTime);
//        employee.setEndTime(endTime);

        if (!StringUtil.isNull(id)) {
            session.update(employee);
        } else {
            session.persist(employee);
        }
        session.getTransaction().commit();
        session.close();

        req.setAttribute("message", "已随机添加员工：" + employee.getName());
        listEmployee(req, resp);

    }

    /**
     * 删除操作
     *
     * @param req
     * @param resp
     */
    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String id = req.getParameter("id");
        Employee employee = session.get(Employee.class, id);
        session.delete(employee);
        session.getTransaction().commit();
        session.close();
    }
}
