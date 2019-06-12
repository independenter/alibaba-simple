package JedisImpl;

import com.alibaba.library.IKBInvocation;
import com.alibaba.library.IKBLibrary;
import com.alibaba.library.Student;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import java.io.*;

public class IKBLibraryImpl implements IKBLibrary {
    private static String FILE_NAME = "./invocation.obj";
    private static Jedis jedis = new Jedis("localhost");

    static class Singleton {
        public static Gson gson = new Gson();
    }

    public static void main(String[] args) {
        IKBLibraryImpl library = new IKBLibraryImpl();
        Student student = new Student();
        student.setName("Mary");
        student.setAge("18");
        student.setAddress("杭州");
        IKBInvocation<Student> invocation = new IKBInvocation<Student>() {
            @Override
            public void preProcess(Student instance) {
                System.out.println("开始绑定学生"+instance);
            }

            @Override
            public void postProcess(Student instance) {
                System.out.println("绑定学生完毕");
            }
        };
        invocation.setInstance(student);
        library.inLibrary(invocation);
        IKBInvocation<Student> temp = (IKBInvocation)library.outLibrary();
        System.out.println(temp.getInstance());;
    }

    @Override
    public void inLibrary(IKBInvocation invocation) {
        writeFile(invocation);
    }

    @Override
    public IKBInvocation outLibrary() {
        return (IKBInvocation)readFile();
    }

    public void writeFile(Object obj) {
        byte[] value = SerializeUtil.serialize(obj);
        jedis.set("student".getBytes(),value);
    }

    public Object readFile() {
        return SerializeUtil.unSerialize(jedis.get("student".getBytes()));
    }
}
