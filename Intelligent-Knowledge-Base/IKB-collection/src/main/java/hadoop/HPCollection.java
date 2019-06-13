package hadoop;

import com.alibaba.collection.IKBCollection;

import java.util.List;

public class HPCollection extends Thread implements IKBCollection {

    static {
        new HPCollection().start();
    }

    @Override
    public void run() {
        getCollection();
    }

    @Override
    public List getCollection() {
        for (int i=0;i<100;i++){
            System.out.println("Hadoop 日志采集");
        }
        return null;
    }
}
