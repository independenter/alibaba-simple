package sop.manager;

import java.util.List;

public interface Service {
    List<Service> getServices();
    Boolean cancelService(String svcname,String svcId);
    Boolean refreshServices(String svcGroup);//上线
    //Boolean refreshServices("default");

}
