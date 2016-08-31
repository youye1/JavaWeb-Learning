package cn.youye.se.servlet;

import cn.youye.se.entity.User;
import cn.youye.se.service.UserDaoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pc on 2016/7/11.
 */
public class LoginServlet extends HttpServlet {
    private UserDaoService userDaoService = new UserDaoService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        if (user != null) {
            String forward = null;
            if (user.getName() != null && user.getName() != "" && user.getPassword() != null && user.getPassword() != "") {
                if (userDaoService.check(user)) {
                    req.setAttribute("msg", "登陆成功");
                } else {
                    req.setAttribute("msg", "登录失败");
                }
            } else {
                req.setAttribute("msg", "用户名或密码为空");
            }

            forward = "/WEB-INF/views/modules/test.jsp";
            RequestDispatcher rd = req.getRequestDispatcher(forward);
            rd.forward(req, resp);

        }
    }

//    @Override
//protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    doPost(req, resp);
//
//}
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("================doPost=========");
//        String username = req.getParameter("username");
//        System.out.println("=============" + username + "=============");
//
//    }
}
