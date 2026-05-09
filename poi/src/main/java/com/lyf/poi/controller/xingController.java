package com.lyf.poi.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("xing")
public class xingController {
    @GetMapping("article/sci")
    public Object batch(@RequestParam("file") MultipartFile file) {
        Map<String, Integer> articlesMap = getArticles(file);
        System.out.println("getArticles:" + articlesMap.toString());
        String floderPath = "C:\\Users\\wanghao\\Downloads\\Documents";
        Map<String, Integer> articlesFromFloder = getArticlesFromFloder(floderPath, articlesMap);
        System.out.println("getArticlesFromFloder:" + articlesMap.toString());
        List<String> notArticles = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : articlesFromFloder.entrySet()) {
            Integer value = entry.getValue();
            if (value == 0) {
                notArticles.add(entry.getKey());
            }
        }
        System.out.println("notArticles:" + notArticles.toString());
        System.out.println("allArticles:" + articlesMap.size());
        System.out.println("articlesFromFloder:" + articlesFromFloder.size());
        System.out.println("notArticlesSize:" + notArticles.size());

        markRed(file, articlesFromFloder);
        return "null";
    }

    private Map<String, Integer> getArticlesFromFloder(String path, Map<String, Integer> articlesMap) {
        File f = new File(path);//获取路径  F:测试目录
        if (!f.exists()) {
            System.out.println(path + " not exists");//不存在就输出
            return articlesMap;
        }

        File fa[] = f.listFiles();//用数组接收  F:笔记总结C#, F:笔记总结if语句.txt
        for (int i = 0; i < fa.length; i++) {//循环遍历
            File fs = fa[i];//获取数组中的第i个
            if (fs.isDirectory()) {
                System.out.println(fs.getName() + " [目录]");//如果是目录就输出
            } else {
                String name = fs.getName().substring(0, fs.getName().length()-4);
                name = containNumStrtoLowerCase(name);
                System.out.println("article："+name);//否则直接输出
                articlesMap.put(name, articlesMap.get(name) + 1);
            }
        }
        return articlesMap;
    }

    private Map<String, Integer> getArticles(@RequestParam("file") MultipartFile file) {
        int count = 0;
        Map<String, Integer> articlesSet = new HashMap<>();
        try {
            InputStream inputStream = file.getInputStream();
            //创建一个工作簿
            Workbook workbook = new XSSFWorkbook(inputStream);
            //得到一个工作表
            Sheet sheet = workbook.getSheet("sheet1");
            //两层for循环来封装数据，注意这个得到的行列数都是从第0行开始
            for (int i = 0; i <= sheet.getLastRowNum() ; i++) {//第0行是标题，所以从第1行开始
                Row row = sheet.getRow(i);
                String articleName = row.getCell(1).getStringCellValue();
                if (StringUtils.isNotBlank(articleName)) {
                    articlesSet.put(articleName, 0);
                    count = count +1;
                }
            }
            System.out.println("articles count is " + count);
            //关闭流
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articlesSet;
    }

    private void markRed(@RequestParam("file") MultipartFile file, Map<String, Integer> articlesFromFloder) {
        try {
            InputStream inputStream = file.getInputStream();
            //创建一个工作簿
            Workbook workbook = new XSSFWorkbook(inputStream);
            //得到一个工作表
            Sheet sheet = workbook.getSheet("sheet1");
            //两层for循环来封装数据，注意这个得到的行列数都是从第0行开始
            CellStyle redCellStyle = null;
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {//第0行是标题，所以从第1行开始
                Row row = sheet.getRow(i);
                String articleName = row.getCell(1).getStringCellValue();
                if (StringUtils.isNotBlank(articleName)) {
                    if (articlesFromFloder.get(articleName) == 0) {
                        Cell cell = row.getCell(1);
                        if (redCellStyle == null) {
                            redCellStyle = workbook.createCellStyle();
                            // 重点：从现有样式克隆style，只修改Font，其它style不变
                            redCellStyle.cloneStyleFrom(cell.getCellStyle());
                            // 获取原有字体
                            Font oldFont = workbook.getFontAt(redCellStyle.getFontIndex());
                            // 创建新字体
                            Font redFont = workbook.createFont();
                            // 重点：保留原字体样式
                            redFont.setFontName(oldFont.getFontName()); // 保留原字体
                            redFont.setFontHeightInPoints(oldFont.getFontHeightInPoints()); // 保留原字体高度
                            redFont.setBold(true); // 加粗
                            redFont.setColor(IndexedColors.RED.getIndex());  // 字体颜色：红色
                            // 设置红色字体
                            redCellStyle.setFont(redFont);
                        }
                        // 设置样式
                        cell.setCellStyle(redCellStyle);
                    }
                }
            }
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\wanghao\\Desktop\\poi-excel-style-demo.xlsx");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();
            //关闭流
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String containNumStrtoLowerCase(String string) {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<string.length();i++){
            char c=string.charAt(i);
            if(c>='A' && c<='Z'){
                c+=32;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
