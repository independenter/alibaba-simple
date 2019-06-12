import com.alibaba.library.IKBInvocation;
import com.alibaba.library.IKBLibrary;
import com.alibaba.library.Student;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPIApp {
    public static void main(String[] args) {
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
        ServiceLoader<IKBLibrary> operations = ServiceLoader.load(IKBLibrary.class);
        Iterator<IKBLibrary> iterator = operations.iterator();

        while (iterator.hasNext()) {
            IKBLibrary library = iterator.next();
            System.out.println(library.outLibrary().getInstance());
        }
    }
}
