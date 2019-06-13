package schedule;

import com.alibaba.collection.IKBCollection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleCollection extends Thread implements IKBCollection {

    public static ArrayList tls;

    static {
        new ScheduleCollection().start();
    }

    @Override
    public void run() {
        getCollection();
    }

    @Override
    public List getCollection() {
        System.out.println("*** 定时调度已经启动，正在等待时间 ***");
        long start = System.currentTimeMillis()+10000;
        while (true){
            if(System.currentTimeMillis()>start){
                break;
            }
        }
        for (int i=0;i<100;i++){
            System.out.println("异常 日志采集");
        }

        return null;
    }
}
