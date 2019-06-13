package sup.base.jdbc;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mybatils {

    private static String url = "jdbc:mysql://localhost:3306/recruit?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
    //private String url = "jdbc:oracle:thin:@localhost:1521";
    private static String username = "root";
    private static String password = "123456";

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    private Mybatils() {

    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            threadLocal.set(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return connection;
        }
    }

    //try-with-resource
    public static Connection getConnection1() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close() {
        Connection conn = threadLocal.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Object> entitysForResultSet(ResultSet rs, Class clazz) throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IntrospectionException, InstantiationException {
        //Role role = new Role();
        Constructor constructor = clazz.getConstructor();
        Object role = constructor.newInstance();
        Field[] fileds = clazz.getDeclaredFields();
        Map map = new HashMap();
        List list = new ArrayList();

        while (rs.next()) {
            for (Field f : fileds) {
                String name = f.getName();
                if (!f.isAccessible()) f.setAccessible(true);
                PropertyDescriptor descriptor = new PropertyDescriptor(name, clazz);
                Method method = descriptor.getWriteMethod();
                method.invoke(role, typeof(rs.getObject(name)));
            }
            list.add(role);
            role = constructor.newInstance();
        }
        return list;
    }

    private static Object typeof(Object o) {
        Object r = o;

        if (o instanceof Timestamp) {
            return r;
        }
        // 将 java.util.Date 转成 java.sql.Date
        if (o instanceof java.util.Date) {
            java.util.Date d = (java.util.Date) o;
            r = new Date(d.getTime());
            return r;
        }
        // 将 Character 或 char 变成 String
        if (o instanceof Character || o.getClass() == char.class) {
            r = String.valueOf(o);
            return r;
        }
        return r;
    }


    public static String getSetMethod(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static List execute(String sql) {
        Connection conn = Mybatils.getConnection();
        try {
            Statement statement = conn.createStatement();
            System.out.println("Execute sql:" + sql);
            ResultSet rs = statement.executeQuery(sql);
            return Mybatils.entitysForResultSet(rs, Role.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } finally {
            close();
            //System.out.println("测试完毕");
        }
        return null;
    }

    public static List queryAll() throws Exception {
        Connection conn = Mybatils.getConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "select * from b_role";
            ResultSet rs = statement.executeQuery(sql);
            return entitysForResultSet(rs, Role.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            close();
            //System.out.println("测试完毕");
        }
        return null;
    }

    public List queryList() throws Exception {
        Connection conn = Mybatils.getConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "select * from b_role";
            ResultSet rs = statement.executeQuery(sql);
            return entitysForResultSet(rs, Role.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            close();
            //System.out.println("测试完毕");
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(queryAll());
        //System.out.println(TranctionalProxy.queryAll());
        System.out.println(new Mybatils().queryList());

    }
}
