package com.lyf.es.service.impl;

import com.lyf.es.service.EsService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EsServiceImpl implements EsService {
    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Override
    public Object esGet() {
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
            SearchResponse response = restHighLevelClient.search(searchRequest);
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

        return sourceAsMap;
    }

}
