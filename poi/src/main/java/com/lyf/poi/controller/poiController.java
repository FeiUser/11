package com.lyf.poi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyf.poi.service.JobChangeService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;

@RestController("poi")
public class poiController {
    private final static Logger logger = LoggerFactory.getLogger(poiController.class);
    @Autowired
    private JobChangeService jobChangeService;

    @GetMapping("excel/batch")
    public Object batch(@RequestParam("file") MultipartFile file) {
        Object data = jobChangeService.batch(file);
        return data;
    }

    @GetMapping("test")
    public Object test() {
        logger.trace("======trace");
        logger.debug("======debug");
        logger.info("======info");
        logger.warn("======warn");
        logger.error("======error");

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 10; i++) {
            JSONObject object = new JSONObject();
            object.put("id", i);
            object.put("name", "test" + i);
            object.put("address", "address" + i);
            jsonArray.add(object);
        }
        return jsonArray;
    }

    @RequestMapping(value = "export", method = {RequestMethod.GET})
    public JSONObject export(HttpServletRequest request, @RequestParam String path, @RequestParam Integer size) throws Exception {
        File file = new File(path + "\\linkedin.xlsx");
        // 创建一个新的Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        // 创建一个工作表
        Sheet sheet = workbook.createSheet("Sheet1");

        String[] fields = {"sid", "full_name", "gender", "remark.job_title_levels", "regions", "email", "mobile_phone", "interests", "company", "remark.industry", "twitter_url", "facebook_url", "summary", "facebook_url", "experience", "education", "skills", "remark.languages"};
        Row sheetRow = sheet.createRow(0);
        for (int s = 0; s < fields.length; s++) {
            Cell cell = sheetRow.createCell(s);
            cell.setCellValue(fields[s]);
        }
//        for (int i = 0; i < queryInfos.size(); i++) {
//            JSONObject jo = JSONObject.fromObject(queryInfos.get(i)).getJSONObject("field");
//            Row row = sheet.createRow(i + 1);
//            for (int j = 0; j < fields.length; j++) {
//                String field = fields[j];
//                String o = jo.containsKey(field) ? jo.get(field).toString() : "";
//                if (field.contains("remark.")) {
//                    field = field.replace("remark.", "");
//                    JSONObject remark = jo.getJSONObject("remark");
//                    o = remark.containsKey(field) ? remark.get(field).toString() : "";
//                }
//                Cell cell = row.createCell(j);
//                if (o.length() >= 32765) {
//                    cell.setCellValue(o.substring(0, 32765));
//                } else {
//                    cell.setCellValue(o);
//                }
//            }
//        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        //关闭
        fileOutputStream.close();
        return null;
    }

    @RequestMapping(value = "updateExcel", method = {RequestMethod.GET})
    public JSONObject export() throws Exception {
        File file = new File("C:\\Users\\wanghao\\Desktop\\comment_person\\Facebook_update.xlsx");
        // 创建一个新的Excel工作簿
        Workbook workbook = new XSSFWorkbook("C:\\Users\\wanghao\\Desktop\\comment_person\\Facebook.xlsx");
        // 创建一个工作表
        Sheet sheet = workbook.getSheet("Sheet1");
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            System.out.println(i);
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            String url = cell.getStringCellValue();
            if (url.contains("&comment_id")) {
                String s = url.split("&comment_id")[0];
                cell.setCellValue(s);
            }else if (url.contains("?comment_id")) {
                String s = url.split("\\?comment_id")[0];
                cell.setCellValue(s);
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        //关闭
        fileOutputStream.close();
        return null;
    }
}
