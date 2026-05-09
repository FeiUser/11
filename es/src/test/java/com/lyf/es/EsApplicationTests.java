package com.lyf.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lyf.es.config.ESConfig;
import com.lyf.es.model.po.tyy.tab.SysUserInfo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class EsApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    ESConfig esConfig;

    @Test
    public void contextLoads() {
        RestHighLevelClient client = esConfig.getRHLClient();
        SearchRequest searchRequest = new SearchRequest("tyy_*");
        searchRequest.types("tyy");

        SearchSourceBuilder searchBuilder = new SearchSourceBuilder();
//        sourceBuilder.from(0);

//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("id_number", "N203230835");
//        boolQuery.must(termQueryBuilder);
//        sourceBuilder.query(boolQuery);
//        searchRequest.source(sourceBuilder);
//        sourceBuilder.fetchSource(new String[]{"id_number", "full_name"}, null);
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("its").field("it").size(10);
        searchBuilder.aggregation(termsAggregationBuilder);
        searchBuilder.size(0);

        searchRequest.source(searchBuilder);
        Map<String, Object> sourceAsMap = new HashMap<>();
        try {
            SearchResponse response = client.search(searchRequest);
            Aggregations aggregations = response.getAggregations();
            Map<String, Aggregation> asMap = aggregations.getAsMap();
            ParsedTerms its = aggregations.get("its");
            List<? extends Terms.Bucket> buckets = its.getBuckets();
            for (Terms.Bucket bucket : buckets) {
                String keyAsString = bucket.getKeyAsString();
                long docCount = bucket.getDocCount();
                System.out.println(keyAsString + ":" + docCount);
            }

            System.out.println(its);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sourceAsMap);
    }

    //    @Autowired
//    private RestTemplate restTemplate;
    @Test
    public void esTest() {
        RestTemplate restTemplate = new RestTemplate();
//        http://localhost:825/_reindex?slices=10&refresh
        String s = "{\n" +
                "    \"source\": {\n" +
                "        \"index\": \"event_mblog_info_ref_event\",\n" +
                "        \"query\": {\n" +
                "            \"bool\": {\n" +
                "                \"should\": [\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"uyghur\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"世界维吾尔代表大会\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"东突厥\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"伊利夏提\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"强制劳动\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"教育营\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"伊斯兰\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"宗教迫害\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"新疆棉花\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"热比娅\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"维吾尔人权\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"维族\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"维稳\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"陈全国\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"马兴瑞\"\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"match_phrase\": {\n" +
                "                            \"content\": \"新疆\"\n" +
                "                        }\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"minimum_should_match\": 1,\n" +
                "                \"must\": [\n" +
                "                    {\n" +
                "                        \"term\": {\n" +
                "                            \"eid\": 2349\n" +
                "                        }\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"dest\": {\n" +
                "        \"index\": \"event_mblog_info_ref_event_xj\"\n" +
                "    },\n" +
                "    \"script\": {\n" +
                "        \"source\": \"ctx._source.is_spam = '0'; ctx._source.is_dup = '0';\"\n" +
                "    }\n" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("elastic", "Ghf93%?W^8u6~BkcIAFLExy4D7_i@5l1");
        HttpEntity<String> httpEntity = new HttpEntity<>(s, headers);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:825/_reindex?slices=10&refresh", HttpMethod.POST, httpEntity, String.class);
        String body = exchange.getBody();
        JSONObject jsonObject = JSON.parseObject(body);
        System.out.println(jsonObject.toString());
    }

    @Test
    public void tabTest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1", "1");
        jsonObject.put("2", "2");
        jsonObject.put("3", "3");

        String s = "1:" + jsonObject.get("1") + "\t";
        s += "2:" + jsonObject.get("2") + "\t";
        s += "3:" + jsonObject.get("3") + "\t";
        System.out.println(s);

        String s2 = "1:" + "\t"
                + "2:" + "\t"
                + "3:";
        System.out.println(s2);
    }

    @Test
    public void replaceTest() {
        String s = "#QurbanMamut (库尔班·马木提)，维吾尔著名的作家、前《新疆文明》杂志的总编2017年11月失踪，后知被送\"再教育营\"关押因\"政治罪\"判处15年一直没有消息出来\"受过高等教育的知识分子被关进拘留营的事实，是当局说法的最好反驳，表明那不是某种旨在让维吾尔人受益的教育项目\"  #良心犯 #一人一推 https://t.co/XsdEKDHE7E";
        System.out.println(s);
        String replace = s.replaceAll("\"", "\\\\\"");
        System.out.println(replace);
    }

    @Test
    public void data2csv() {
        List<SysUserInfo> list = new ArrayList<>();
        SysUserInfo sysUserInfo = new SysUserInfo();
        sysUserInfo.setId(1);
        sysUserInfo.setEmail("123@qq.com");
        sysUserInfo.setCreateTime(new Date());
        list.add(sysUserInfo);
        SysUserInfo sysUserInfo2 = new SysUserInfo();
        sysUserInfo2.setId(1);
        sysUserInfo2.setEmail("123@qq.com");
        sysUserInfo2.setCreateTime(new Date());
        list.add(sysUserInfo2);

        File file = new File("./");
        JSONArray jsonArray = JSONArray.parseArray(JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue));
        if (jsonArray.size() > 0) {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            Set<String> keySet = jsonObject.keySet();
//            ********
            File cvsFile = new File(file, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())  + ".csv");
            try (CSVPrinter printer = new CSVPrinter(new FileWriter(cvsFile), CSVFormat.DEFAULT)) {
                printer.printRecord(keySet);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject json = jsonArray.getJSONObject(i);
                    List<Object> data = new ArrayList<>();
                    for (String key : keySet) {
                        Object o = json.get(key);
                        data.add(o);
                    }
                    printer.printRecord(data);
                }
                printer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
