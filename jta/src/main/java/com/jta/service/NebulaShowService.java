package com.jta.service;


import com.jta.entity.ng.tyy.pojo.tag.NPerson;

import java.util.List;

public interface NebulaShowService {
    List<NPerson> getByField(String field, String value, int pageNum, int pageSize, int all);

    Object getRelationByField(Integer id, String field, String value);

    NPerson getPersonById(Integer id);

    Long showStats();
}
