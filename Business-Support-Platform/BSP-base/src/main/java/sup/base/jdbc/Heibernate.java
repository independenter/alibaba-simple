package sup.base.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class Heibernate {

    public static void main(String[] args) throws Exception {
//        System.out.println(System.getProperty("user.dir"));
//        Configuration.ReadXml("test\\javat\\view\\spring\\jdbc\\heibernate.cfg.xml");
//        System.out.println(Heibernate.queryAll());

        MySQLDialect src = new MySQLDialect();
        TranctionalProxy handler = new TranctionalProxy(src);
        //得到系统ClassLoader
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        //接口数组
        Class[] clazzs = {HerbernateDialect.class};
        HerbernateDialect dialect = (HerbernateDialect) Proxy.newProxyInstance(loader, clazzs, handler);
        dialect.queryAllWithExtend();
//        System.out.println(Heibernate.queryAllWithExtend());
    }

    private static Map config = Configuration.ReadXml("test\\javat\\view\\spring\\jdbc\\heibernate.cfg.xml");

    static class Configuration {
        private static DataSource getDataSource() {
            BasicDataSource dataSource = new BasicDataSource();
            InputStream in = null;
            try {
                in = Heibernate.class.getResourceAsStream("/config.properties");
                Properties prop = new Properties();
                prop.load(in);
                dataSource.setDriverClassName(prop.getProperty("jdbc.driverName"));
                dataSource.setUrl(prop.getProperty("jdbc.url"));
                dataSource.setUsername(prop.getProperty("jdbc.user"));
                dataSource.setPassword(prop.getProperty("jdbc.pwd"));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return dataSource;
        }

        private static Map ReadXml(String filename) {
            Map map = new HashMap();
            File file = new File(filename);
            SAXReader reader = new SAXReader();
            List<String> mapping = new LinkedList<String>();
            map.put("mapping", mapping);
            try {
                Document doc = reader.read(file);
                Element root = doc.getRootElement();
                Element factory = root.element("session-factory");
                List<Element> eles = factory.elements();
                eles.forEach(e -> {
                    if ("property".equals(e.getName())) {
                        map.put(e.attribute("name").getValue(), e.getText());
                    } else if ("mapping".equals(e.getName())) {
                        mapping.add(e.attribute("resource").getValue());
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(map);
                return map;
            }
        }
    }

    public static List queryAll() throws Exception {
        Class clazz = Class.forName(config.get("hibernate.dialect") == null ? "javat.view.spring.jdbc.MySQLDialect" : String.valueOf(config.get("hibernate.dialect")));
        Constructor constructor = clazz.getDeclaredConstructor();
        if (!constructor.isAccessible()) constructor.setAccessible(true);
        Object object = constructor.newInstance();
        Method method = clazz.getDeclaredMethod("queryAll");
        return (List) method.invoke(object);
    }

    public static List queryAllWithExtend() throws Exception {
        Class clazz = Class.forName(config.get("hibernate.dialect") == null ? "javat.view.spring.jdbc.MySQLDialect" : String.valueOf(config.get("hibernate.dialect")));
        Constructor constructor = clazz.getDeclaredConstructor();
        if (!constructor.isAccessible()) constructor.setAccessible(true);
        HerbernateDialect dialect = (HerbernateDialect) constructor.newInstance();
        return dialect.queryAllWithExtend();
    }
}
