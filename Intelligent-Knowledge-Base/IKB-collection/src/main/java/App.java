import com.alibaba.collection.IKBCollection;

import java.util.Iterator;
import java.util.ServiceLoader;

public class App {
    public static void main(String[] args) {
        ServiceLoader<IKBCollection> operations = ServiceLoader.load(IKBCollection.class);
        Iterator<IKBCollection> iterator = operations.iterator();

        while (iterator.hasNext()) {
            iterator.next();
        }
    }
}
