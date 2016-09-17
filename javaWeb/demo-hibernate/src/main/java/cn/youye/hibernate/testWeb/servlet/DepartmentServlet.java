package cn.youye.hibernate.testWeb.servlet;

import cn.youye.hibernate.testWeb.entity.Department;
import cn.youye.hibernate.testWeb.entity.Employee;
import cn.youye.hibernate.util.HibernateUtil;
import cn.youye.hibernate.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by pc on 2016/9/10.
 */
public class DepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if ("form".equals(action)) {
            form(req, resp);
        } else if ("save".equals(action)) {
            save(req, resp);
        } else if ("query".equals(action)) {
            query(req, resp);
        } else {
            list(req, resp);
        }
    }

    /**
     * 添加部门或编辑
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void form(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = new Department();
        String id = req.getParameter("id");
        if (!StringUtil.isNull(id)) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            department = session.get(Department.class, id);
        }
        req.setAttribute("id", department.getId());
        req.setAttribute("name", department.getName());
        req.setAttribute("description", department.getDescription());
        if (!StringUtil.isNull(department.getManager())) {
            req.setAttribute("managerId", department.getManager().getId());
            req.setAttribute("managerName", department.getManager().getName());
        } else {
            req.setAttribute("managerNa", "");
            req.setAttribute("managerId", "");
        }
        req.getRequestDispatcher("/WEB-INF/views/testWeb/department/form.jsp").forward(req, resp);
        return;
    }

    /**
     * 保存界面
     *
     * @param req
     * @param resp
     */
    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String managerId = req.getParameter("managerId");
        String description = req.getParameter("description");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Department> departments = session.createQuery("select d from Department d where d.name = :name").setParameter("name", name).list();
        Department department = null;

        if (StringUtil.isNull(id)) {

            //判断部门是否已存在
            if (departments.size() > 0) {
                req.setAttribute("message", "部门名称" + name + "已存在,请重新命名");
                req.getRequestDispatcher("/WEB-INF/views/testWeb/department/form.jsp").forward(req, resp);
                return;
            }
            department = new Department();
            department.setName(name);
            department.setDescription(description);
            if (!StringUtil.isNull(managerId)) {
                Employee employee = session.get(Employee.class, managerId);
                department.setManager(employee);
            }
            session.persist(department);
        } else {

            department = session.get(Department.class, id);
            if (departments.size() > 0 && departments.get(0).getName() != department.getName()) {
                req.setAttribute("message", "部门名称" + name + "已存在,请重新命名");
                req.getRequestDispatcher("/WEB-INF/views/testWeb/department/form.jsp").forward(req, resp);
                return;
            }
            department.setName(name);
            department.setDescription(description);
            if (!StringUtil.isNull(managerId)) {
                Employee employee = session.get(Employee.class, managerId);
                department.setManager(employee);
            }
            session.persist(department);
        }
        session.getTransaction().commit();
        session.close();
        req.setAttribute("message", "部门" + name + "添加成功！");
        list(req, resp);
    }

    /**
     * 查询经理
     */
    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        JSONArray json = new JSONArray();
        JSONObject object = new JSONObject();
        List<Employee> employees = session.createQuery("select e from Employee e " +
                "where e.name like '%" + key + "%' order by e.name desc ").list();
        if (employees.size() > 0) {
            for (Employee e : employees) {
                object = new JSONObject();
                object.put("name", e.getName());
                object.put("id", e.getId());
                json.add(object);
            }
        }
        PrintWriter out = resp.getWriter();
        out.write(json.toJSONString());
    }

    /**
     * 部门列表
     *
     * @param req
     * @param resp
     */
    private void list(HttpServletRequest req, HttpServletResponse resp) {

        String sort = req.getParameter("sort");
        String order = req.getParameter("order");
        if (StringUtil.isNull(sort)) {
            sort = "id";  //默认为id排序
        }
        if (StringUtil.isNull(order)) {
            order = "desc";   //默认为desc降序排序
        }

        String departmentName = req.getParameter("departmentName");
        String managerName = req.getParameter("managerName");
        String employeesSize = req.getParameter("employeesSize");
        String employeesOperator = req.getParameter("employeesOperator");

        String where = "";
        if (!StringUtil.isNull(departmentName)) {
            if (where.length() != 0) {
                where += " and ";
            }
            where += " d.name like '%" + departmentName + "%' ";
        }
        if (!StringUtil.isNull(managerName)) {
            if (where.length() != 0) {
                where += " and ";
            }
            where += " d.manager.name like '%" + managerName + "%' ";
        }
        if (!StringUtil.isNull(employeesSize)) {
            if (where.length() != 0) {
                where += " and ";
            }
            where += " d.employees.size " + employeesOperator + " " + employeesSize + " ";
        }

        String hql = " from Department d ";
        if (!StringUtil.isNull(where)) {
            hql += " where " + where;
        }

        hql += " order by d." + sort + " " + order;
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Department> departments = session.createQuery("select d " + hql).list();

        for (Department department : departments) {
            department.getEmployees().size();
        }
        session.close();
        for (Department depart : departments) {
            if (depart.getEmployees() == null) {
                Employee e = new Employee();
                e.setName("");
                depart.setManager(e);
            }
        }
        req.setAttribute("departments", departments);
        req.setAttribute("order", order);
        req.setAttribute("sort", sort);
        req.setAttribute("url", StringUtil.getURL(req));

        if (req.getAttribute("message") == null) {
            req.setAttribute("message", "HQL: " + hql);
        }
        try {
            req.getRequestDispatcher("/WEB-INF/views/testWeb/department/list.jsp").forward(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return;
    }

}
