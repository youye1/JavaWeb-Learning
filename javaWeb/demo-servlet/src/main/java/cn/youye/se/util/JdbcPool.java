package cn.youye.se.util;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by pc on 2016/7/11.
 */
public class JdbcPool implements DataSource {

    private static LinkedList<Connection> listConn = new LinkedList<Connection>();

    static {
        //实现数据库连接
        InputStream in = JdbcPool.class.getClassLoader().getResourceAsStream("db.properties");
        Properties prop = new Properties();
        try {
            //加载数据库连接文件
            prop.load(in);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");

            //加载连接池连接数
            int jdbcPoolInitSize = Integer.parseInt(prop.getProperty("jdbcPoolInitSize"));

            //加载数据库驱动
            Class.forName(driver);
            for (int i = 0; i < jdbcPoolInitSize; i++) {
                Connection conn = DriverManager.getConnection(url, username, password);
                listConn.add(conn);
                System.out.println("连接池连接数为：" + (i + 1));
            }

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 连接数据库
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        if (listConn.size() > 0) {
            final Connection conn = listConn.removeFirst();
            return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(),
                    conn.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if (!method.getName().equals("close")) {
                                return method.invoke(conn, args);
                            } else {
                                listConn.add(conn);
                                System.out.println("连接池连接数为：" + listConn.size());
                                return null;
                            }
                        }
                    });
        } else {
            throw new RuntimeException("数据库繁忙");
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
