package commonIoImpl;

import com.alibaba.library.IKBInvocation;
import com.alibaba.library.IKBLibrary;
import com.alibaba.library.Student;

import java.io.*;

public class IKBLibraryImpl implements IKBLibrary {
    private static String FILE_NAME = "./invocation.obj";

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
        library.writeFile(invocation);
        IKBInvocation<Student> temp = (IKBInvocation)library.readFile();
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
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(obj);
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object readFile() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            return ois.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
