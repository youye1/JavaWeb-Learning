package cn.youye.hibernate.test1.servlet;

import cn.youye.hibernate.test1.dao.BaseDao;
import cn.youye.hibernate.test1.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pc on 2016/9/8.
 */
public class StudentServlet extends HttpServlet {
    private BaseDao<Student> baseDao = new BaseDao<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            edit(req, resp);//显示添加页面
        } else if ("add".equals(action)) {
            add(req, resp);//插入一条记录
        } else if ("save".equals(action)) {
            save(req, resp);//更新数据
        } else if ("list".equals(action)) {
            list(req, resp);//显示list页面
        } else if ("delete".equals(action)) {
            delete(req, resp); //删除对应id的记录
        } else {
            list(req, resp);
        }
    }

    /**
     * 插入数据到数据库
     *
     * @param req
     * @param resp
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String birthday = req.getParameter("birthday");
        Date date = new Date();
        Student student = new Student();
        student.setName(req.getParameter("name"));
        student.setGender(req.getParameter("gender"));
        student.setAddress(req.getParameter("address"));
        student.setBirthday(date);
        if (new Integer(student.getId())!=null){
            baseDao.update(student);
        }else {
            baseDao.create(student);
        }
        req.getRequestDispatcher("/WEB-INF/views/test1/student/studentList.jsp").forward(req, resp);
    }

    /**
     * 修改页面
     *
     * @param req
     * @param resp
     */
    public void edit(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Student student = null;
        if (req.getParameter("id") == null) {
            student = new Student();
        } else {
            int id = Integer.parseInt(req.getParameter("id"));
            student = baseDao.get(Student.class, id);
        }
        req.setAttribute("student", student);
        req.getRequestDispatcher("/WEB-INF/views/test1/student/studentForm.jsp").forward(req, resp);

    }

    /**
     * 保存到数据库
     *
     * @param req
     * @param resp
     */
    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String birthday = req.getParameter("birthday");
        Date date = new Date();
        Student student = new Student();
        student.setName(req.getParameter("name"));
        student.setGender(req.getParameter("gender"));
        student.setAddress(req.getParameter("address"));
        student.setBirthday(date);
        if (id!=0) {
            student.setId(id);
            baseDao.update(student);
        }else {
            baseDao.create(student);
        }
        req.getRequestDispatcher("/studentServlet?action=list").forward(req, resp);
    }

    /**
     * list列表页面
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("list", baseDao.findList("from Student"));
        req.getRequestDispatcher("/WEB-INF/views/test1/student/studentList.jsp").forward(req, resp);
    }

    /**
     * 删除
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = baseDao.get(Student.class, id);
        baseDao.delete(student);
        req.setAttribute("list", baseDao.findList("from Student"));
        req.getRequestDispatcher("/WEB-INF/views/test1/student/studentList.jsp").forward(req, resp);
    }


}
