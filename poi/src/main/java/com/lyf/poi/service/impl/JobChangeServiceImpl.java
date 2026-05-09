package com.lyf.poi.service.impl;

import com.lyf.poi.service.JobChangeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class JobChangeServiceImpl implements JobChangeService {

    @Override
    public Object batch(MultipartFile file){
        int count = 0;
        try {
            InputStream inputStream = file.getInputStream();
            //创建一个工作簿
            Workbook workbook = new XSSFWorkbook(inputStream);
            //得到一个工作表
            Sheet sheet = workbook.getSheet("sheet1");
            //两层for循环来封装数据，注意这个得到的行列数都是从第0行开始
            for (int i = 1; i <= sheet.getLastRowNum() ; i++) {//第0行是标题，所以从第1行开始
                Row row = sheet.getRow(i);
                String wikiUrl = row.getCell(2).getStringCellValue();
                if (StringUtils.isNotBlank(wikiUrl)) {
                    String name = row.getCell(0).getStringCellValue();
                    System.out.println(name);
//                    jobChangeDetectionRepository.updateWikiUrlByName(name, wikiUrl);
                    count = count +1;
                }
            }
//            log.info("-----------修改行数：{}", count);
            //关闭流
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
