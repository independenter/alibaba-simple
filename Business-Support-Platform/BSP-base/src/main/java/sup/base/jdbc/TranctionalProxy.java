package sup.base.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class TranctionalProxy implements InvocationHandler {

    private Object target;

    public TranctionalProxy(Object target) {
        this.target = target;
    }

    public static List queryAll() throws Exception {
        //@Before
        Before();
        List result = Mybatils.queryAll();
        After();
        return result;
    }

    public static void Before() {
        System.out.println("执行前");
    }

    public static void After() {
        System.out.println("执行后");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----before-----");
        Object result = method.invoke(target, args);
        System.out.println("-----end-----");
        return null;
    }

    // 生成代理对象
    public Object getProxy() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(loader, interfaces, this);
    }
}
