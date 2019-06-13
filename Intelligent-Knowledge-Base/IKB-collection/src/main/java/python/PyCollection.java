package python;

import com.alibaba.collection.IKBCollection;

import java.util.List;

public class PyCollection extends Thread implements IKBCollection {
    static {
        new PyCollection().start();
    }

    @Override
    public void run() {
        getCollection();
    }

    @Override
    public List getCollection() {
        for (int i=0;i<100;i++){
            System.out.println("Python 信息采集");
        }
        return null;
    }
}
