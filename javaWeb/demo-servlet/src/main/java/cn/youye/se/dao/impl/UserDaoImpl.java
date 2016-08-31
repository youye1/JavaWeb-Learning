package cn.youye.se.dao.impl;


import cn.youye.se.dao.UserDao;
import cn.youye.se.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pc on 2016/7/11.
 */
public class UserDaoImpl implements UserDao {

    @Override
    public ResultSet get(Connection con, User user) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * from user WHERE user_name=? and  password=? ");
        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        return ps.executeQuery();
    }
}
