package com.jta.controller;

import com.jta.entity.ng.tyy.pojo.tag.NPerson;
import com.jta.service.NebulaShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("graph")
@RestController
public class NebulaShowController {
    @Autowired
    private NebulaShowService nebulaShowService;

    /**
     * 左侧分类查询 点击某个人物的属性图
     * @param id
     * @return
     */
    @GetMapping("getPersonById")
    public Object getPersonById(@RequestParam(value = "id") Integer id) {
        NPerson result = nebulaShowService.getPersonById(id);
        return result;
    }

    @GetMapping("showStats")
    public Object showStats() {
        long result = nebulaShowService.showStats();
        return result;
    }

    /**
     * 左侧分类查询
     * @param field
     * @param value
     * @param pageNum
     * @param pageSize
     * @param all
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("getByField")
    public Object getByField(@RequestParam(value = "field") String field,
                             @RequestParam(value = "value") String value,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                             @RequestParam(value = "all", defaultValue = "1") int all) throws UnsupportedEncodingException {
        List<String> encodeField = new ArrayList<>();
        encodeField.add("mobilePhone");
        if (encodeField.contains(field)) {
            value = URLEncoder.encode(value, "UTF-8");
        }
        List<NPerson> nPersonList = nebulaShowService.getByField(field, value, pageNum, pageSize, all);
        return nPersonList;
    }

    /**
     * 通过人物的属性进行扩展关联的人员
     * @param id
     * @param field
     * @param value
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("getRelationByField")
    public Object getRelationByField(@RequestParam(value = "id") Integer id,
                                     @RequestParam(value = "field") String field,
                                     @RequestParam(value = "value") String value) throws UnsupportedEncodingException {
        List<String> encodeField = new ArrayList<>();
        encodeField.add("mobilePhone");
        if (encodeField.contains(field)) {
            value = URLEncoder.encode(value, "UTF-8");
        }
        Object result = nebulaShowService.getRelationByField(id, field, value);
        return result;
    }
}
