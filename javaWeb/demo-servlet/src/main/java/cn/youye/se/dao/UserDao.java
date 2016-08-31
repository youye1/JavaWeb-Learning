package cn.youye.se.dao;


import cn.youye.se.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pc on 2016/7/11.
 */
public interface UserDao {
    public ResultSet get(Connection con, User user) throws SQLException;
}
