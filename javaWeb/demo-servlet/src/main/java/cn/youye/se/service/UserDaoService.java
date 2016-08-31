package cn.youye.se.service;


import cn.youye.se.dao.UserDao;
import cn.youye.se.dao.impl.UserDaoImpl;
import cn.youye.se.entity.User;
import cn.youye.se.util.JdbcPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pc on 2016/7/11.
 */
public class UserDaoService {
    private UserDao userDao = new UserDaoImpl();
    private JdbcPool jdbcPool = new JdbcPool();

    public boolean check(User user) {
        Connection conn = null;
        try {
            conn = jdbcPool.getConnection();
            ResultSet rs = userDao.get(conn, user);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
