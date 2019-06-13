package sup.base.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class MySQLDialect implements HerbernateDialect {

    public List queryAll() {
        Connection conn = Mybatils.getConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "select * from b_role";
            ResultSet rs = statement.executeQuery(sql);
            return Mybatils.entitysForResultSet(rs, Role.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Mybatils.close();
        }
        return null;
    }

    @Override
    public List queryAllWithExtend() {
        Connection conn = Mybatils.getConnection();
        try {
            Statement statement = conn.createStatement();
            String sql = "select * from b_role";
            ResultSet rs = statement.executeQuery(sql);
            return Mybatils.entitysForResultSet(rs, Role.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
