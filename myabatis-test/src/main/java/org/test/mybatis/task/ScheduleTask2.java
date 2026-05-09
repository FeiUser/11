package org.test.mybatis.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.test.mybatis.service.FileService;

import java.util.ArrayList;
import java.util.List;

//@Component
public class ScheduleTask2 {

    @Autowired
    private FileService fileServer;
    /**
     * fixedDelayString:等到方法执行完成后延迟配置的时间再次执行该方法
     */
//    @Scheduled(initialDelayString = "5000" ,fixedDelayString = "2000")
    public void task1() {
        int count = 0;
        String filePath = "E:\\lyf\\tyy\\20240202\\";
        List<String> list = new ArrayList<>();
        list.add("conf");
        list.add("mapping");
        list.forEach( floder -> {
            try {
                fileServer.fileServer(filePath, floder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("finished!");
    }
}
