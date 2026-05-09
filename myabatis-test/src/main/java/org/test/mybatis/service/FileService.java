package org.test.mybatis.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {
    @Async("asyncServiceExecutor")
    public void fileServer(String filePath, String floder) throws InterruptedException {
        String folderPath = filePath + floder;
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("文件夹不存在:" + folderPath);
            return;
        }

        File[] txtFiles = folder.listFiles();
        if (txtFiles != null) {
            for (File file : txtFiles) {
                System.out.println(Thread.currentThread().getName() + " 文件夹" + folderPath + " 中的文件:" + file.getName());
                Thread.sleep(50000);
                System.out.println("ok");
            }
        } else {
            System.out.println("文件夹" + folderPath + "中没有txt文件");
        }
    }
}
