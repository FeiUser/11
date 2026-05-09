package com.jta.controller;

import com.alibaba.fastjson.JSONObject;
import com.jta.entity.mysql.test1.rep.UserRepository;
import com.jta.entity.mysql.test1.tab.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/addUser")
    public Object addUser(){
        User user = new User();
        user.setUsername("123");
        user.setPassword("123");
        userRepository.save(user);
        return null;
    }
    @GetMapping("/findById")
    public Object findById(@RequestBody JSONObject jsonObject){
        String name = jsonObject.getString("name");
        String password = jsonObject.getString("password");
        Specification<User> userSpecification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (StringUtils.isNotEmpty(name)) {
                    predicateList.add(criteriaBuilder.equal(root.get("username"), name));
                }
                if (StringUtils.isNotEmpty(password)) {
                    predicateList.add(criteriaBuilder.equal(root.get("password"), password));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        List<User> all = userRepository.findAll(userSpecification);
        return null;
    }
}
