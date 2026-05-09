package com.jta.service.impl;


import com.jta.entity.ng.tyy.dao.NPersonDao;
import com.jta.entity.ng.tyy.pojo.edge.EnglishNameE;
import com.jta.entity.ng.tyy.pojo.edge.FullNameE;
import com.jta.entity.ng.tyy.pojo.result.PersonEnglishNamePerson;
import com.jta.entity.ng.tyy.pojo.result.PersonFullNamePerson;
import com.jta.entity.ng.tyy.pojo.tag.NPerson;
import com.jta.service.NebulaShowService;
import com.vesoft.nebula.Row;
import com.vesoft.nebula.client.graph.data.ResultSet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NebulaShowServiceImpl implements NebulaShowService {
    @Value("${n:10}")
    private Integer n;

    @Autowired
    private NPersonDao nPersonDao;

//    @Override
//    public Object getRelationByFullName(Integer id, String fullName) {
//        Boolean isEnglishName = KeywordsUtil.englishName(name);
//        NPerson sourcePerson;
//        if (isEnglishName) {
//            sourcePerson = nPersonDao.findPersonByEnglishNameLimit1(name).get(0);
//            if (null != sourcePerson) {
//                Integer id = sourcePerson.getId();
//                return nPersonDao.findPersonEnglishNamePerson(id);
//            }
//        } else {
//            sourcePerson = nPersonDao.findPersonByFullNameLimit1(name).get(0);
//            if (null != sourcePerson) {
//                Integer id = sourcePerson.getId();
//                return nPersonDao.findPersonFullNamePerson(id);
//            }
//        }
//
//        return new ArrayList<>();
//    }

    @Override
    public NPerson getPersonById(Integer id) {
        return nPersonDao.findById(id);
    }

    @Override
    public Long showStats() {
        long count = 0L;
        ResultSet resultSet = nPersonDao.showStatus();
        List<Row> rows = resultSet.getRows();
        for (Row row : rows) {
            String type = new String(row.getValues().get(0).getSVal());
            System.out.println(type);
            String name = new String(row.getValues().get(1).getSVal());
            System.out.println(name);
            if ("person".equals(name) && "Tag".equals(type)) {
                count = row.getValues().get(2).getIVal();
                break;
            }
        }
        return count;
    }


    @Override
    public List<NPerson> getByField(String field, String value, int pageNum, int pageSize, int all) {
        Integer from = (pageNum - 1) * pageSize;

        List<NPerson> result = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        if ("fullName".equals(field)) {
            if (1 == all) {
                ResultSet resultSet = nPersonDao.findIdsByFullNameEPage((pageNum - 1) * pageSize, pageSize);
                List<Row> rows = resultSet.getRows();
                ids = rows.stream()
                        .map(
                                row -> (int)row.getValues().get(0).getIVal()
                        )
                        .collect(Collectors.toList());
                result = nPersonDao.selectByIds(ids);
            } else {
                result = nPersonDao.findPersonByFullNamePage(value, from, pageSize);
            }
            result = result.stream().filter(s ->
                    StringUtils.isNotEmpty(s.getFullName())
            ).collect(Collectors.toList());
        } else if ("englishName".equals(field)) {
            if (1 == all) {
                ResultSet resultSet = nPersonDao.findIdsByEnglishNameEPage((pageNum - 1) * pageSize, pageSize);
                List<Row> rows = resultSet.getRows();
                ids = rows.stream()
                        .map(
                                row -> (int)row.getValues().get(0).getIVal()
                        )
                        .collect(Collectors.toList());
                result = nPersonDao.selectByIds(ids);
            } else {
                result = nPersonDao.findPersonByEnglishNamePage(value, from, pageSize);
            }
            result = result.stream().filter(s ->
                    StringUtils.isNotEmpty(s.getEnglishName())
            ).collect(Collectors.toList());
        }else if ("industry".equals(field)) {
            if (1 == all) {
                ResultSet resultSet = nPersonDao.findIdsByIndustryEPage((pageNum - 1) * pageSize, pageSize);
                List<Row> rows = resultSet.getRows();
                ids = rows.stream()
                        .map(
                                row -> (int)row.getValues().get(0).getIVal()
                        )
                        .collect(Collectors.toList());
                result = nPersonDao.selectByIds(ids);
            } else {
                result = nPersonDao.findPersonByIndustryPage(value, from, pageSize);
            }
            result = result.stream().filter(s ->
                    StringUtils.isNotEmpty(s.getIndustry()) && !s.getIndustry().contains("元")
            ).collect(Collectors.toList());
        } else if ("idNumber".equals(field)) {
            if (1 == all) {
                ResultSet resultSet = nPersonDao.findIdsByIdNumberIdEPage((pageNum - 1) * pageSize, pageSize);
                List<Row> rows = resultSet.getRows();
                ids = rows.stream()
                        .map(
                                row -> (int)row.getValues().get(0).getIVal()
                        )
                        .collect(Collectors.toList());
                result = nPersonDao.selectByIds(ids);
            } else {
                result = nPersonDao.findPersonByIdNumberPage(value, from, pageSize);
            }
            result = result.stream().filter(s ->
                    StringUtils.isNotEmpty(s.getIdNumber())
            ).collect(Collectors.toList());
        } else if ("facebookId".equals(field)) {
            if (1 == all) {
                ResultSet resultSet = nPersonDao.findIdsByFacebookIdEPage((pageNum - 1) * pageSize, pageSize);
                List<Row> rows = resultSet.getRows();
                ids = rows.stream()
                        .map(
                                row -> (int)row.getValues().get(0).getIVal()
                        )
                        .collect(Collectors.toList());
                result = nPersonDao.selectByIds(ids);
            } else {
                result = nPersonDao.findPersonByFacebookIdPage(value, from, pageSize);
            }
            result = result.stream().filter(s ->
                    StringUtils.isNotEmpty(s.getFacebookId())
            ).collect(Collectors.toList());
        } else if ("mobilePhone".equals(field)) {
            if (1 == all) {
                ResultSet resultSet = nPersonDao.findIdsByMobilePhoneIdEPage((pageNum - 1) * pageSize, pageSize);
                List<Row> rows = resultSet.getRows();
                ids = rows.stream()
                        .map(
                                row -> (int)row.getValues().get(0).getIVal()
                        )
                        .collect(Collectors.toList());
                result = nPersonDao.selectByIds(ids);
            } else {
                result = nPersonDao.findPersonByMobilePhonePage(value, from, pageSize);
            }
            result = result.stream().filter(s ->
                    StringUtils.isNotEmpty(s.getMobilePhone())
            ).collect(Collectors.toList());
        } else if ("twitterUrl".equals(field)) {
            if (1 == all) {
                ResultSet resultSet = nPersonDao.findIdsByTwitterUrlIdEPage((pageNum - 1) * pageSize, pageSize);
                List<Row> rows = resultSet.getRows();
                ids = rows.stream()
                        .map(
                                row -> (int)row.getValues().get(0).getIVal()
                        )
                        .collect(Collectors.toList());
                result = nPersonDao.selectByIds(ids);
            } else {
                result = nPersonDao.findPersonByTwitterUrlPage(value, from, pageSize);
            }
            result = result.stream().filter(s ->
                    StringUtils.isNotEmpty(s.getTtUserUrl())
            ).collect(Collectors.toList());
        } else if ("email".equals(field)) {
            if (1 == all) {
                ResultSet resultSet = nPersonDao.findIdsByEmailIdEPage((pageNum - 1) * pageSize, pageSize);
                List<Row> rows = resultSet.getRows();
                ids = rows.stream()
                        .map(
                                row -> (int)row.getValues().get(0).getIVal()
                        )
                        .collect(Collectors.toList());
                result = nPersonDao.selectByIds(ids);
            } else {
                result = nPersonDao.findPersonByEmailPage(value, from, pageSize);
            }
            result = result.stream().filter(s ->
                    StringUtils.isNotEmpty(s.getEmail())
            ).collect(Collectors.toList());
        } else if ("jobCompany".equals(field)) {
            if (1 == all) {
                ResultSet resultSet = nPersonDao.findIdsByJobCompanyIdEPage((pageNum - 1) * pageSize, pageSize);
                List<Row> rows = resultSet.getRows();
                ids = rows.stream()
                        .map(
                                row -> (int)row.getValues().get(0).getIVal()
                        )
                        .collect(Collectors.toList());
                result = nPersonDao.selectByIds(ids);
            } else {
                result = nPersonDao.findPersonByJobCompanyPage(value, from, pageSize);
            }
            result = result.stream().filter(s ->
                    StringUtils.isNotEmpty(s.getJobCompanyName())
            ).collect(Collectors.toList());
        }

        return result;
    }

    @Override
    public Object getRelationByField(Integer id, String field, String value) {
        int n = 50;
        NPerson src = nPersonDao.findById(id);
        List<NPerson> dist = new ArrayList<>();
        List<Object> result = new ArrayList<>();
        if ("fullName".equals(field) && StringUtils.isNotEmpty(value)) {
            dist = nPersonDao.findPersonByFullNameLimitN(value, n);
            dist.removeIf(person -> person.getId().equals(id));
            for (NPerson nPerson : dist) {
                result.add(new PersonFullNamePerson(src, new FullNameE(value), nPerson));
            }
        }
        if ("englishName".equals(field) && StringUtils.isNotEmpty(value)) {
            dist = nPersonDao.findPersonByEnglishNameLimitN(value, n);
            dist.removeIf(person -> person.getId().equals(id));
            for (NPerson nPerson : dist) {
                result.add(new PersonEnglishNamePerson(src, new EnglishNameE(value), nPerson));
            }
        }
        if ("industry".equals(field) && StringUtils.isNotEmpty(value)) {
            dist = nPersonDao.findPersonByIndustryLimitN(value, n);
            dist.removeIf(person -> person.getId().equals(id));
            for (NPerson nPerson : dist) {
                result.add(new PersonFullNamePerson(src, new FullNameE(value), nPerson));
            }
        }
        if ("idNumber".equals(field) && StringUtils.isNotEmpty(value)) {
            dist = nPersonDao.findPersonByIdNumberLimitN(value, n);
            dist.removeIf(person -> person.getId().equals(id));
            for (NPerson nPerson : dist) {
                result.add(new PersonEnglishNamePerson(src, new EnglishNameE(value), nPerson));
            }
        }
        if ("facebookId".equals(field) && StringUtils.isNotEmpty(value)) {
            value = value.replace("https://www.facebook.com/profile.php?id=", "");
            dist = nPersonDao.findPersonByFacebookIdLimitN(value, n);
            dist.removeIf(person -> person.getId().equals(id));
            for (NPerson nPerson : dist) {
                result.add(new PersonEnglishNamePerson(src, new EnglishNameE(value), nPerson));
            }
        }
        if ("mobilePhone".equals(field) && StringUtils.isNotEmpty(value)) {
            dist = nPersonDao.findPersonByMobilePhoneLimitN(value, n);
            dist.removeIf(person -> person.getId().equals(id));
            for (NPerson nPerson : dist) {
                result.add(new PersonEnglishNamePerson(src, new EnglishNameE(value), nPerson));
            }
        }
        if ("twitterUrl".equals(field) && StringUtils.isNotEmpty(value)) {
            dist = nPersonDao.findPersonByTwitterUrlLimitN(value, n);
            dist.removeIf(person -> person.getId().equals(id));
            for (NPerson nPerson : dist) {
                result.add(new PersonEnglishNamePerson(src, new EnglishNameE(value), nPerson));
            }
        }
        if ("email".equals(field) && StringUtils.isNotEmpty(value)) {
            dist = nPersonDao.findPersonByEmailLimitN(value, n);
            dist.removeIf(person -> person.getId().equals(id));
            for (NPerson nPerson : dist) {
                result.add(new PersonEnglishNamePerson(src, new EnglishNameE(value), nPerson));
            }
        }
        if ("jobCompany".equals(field) && StringUtils.isNotEmpty(value)) {
            dist = nPersonDao.findPersonByJobCompanyLimitN(value, n);
            dist.removeIf(person -> person.getId().equals(id));
            for (NPerson nPerson : dist) {
                result.add(new PersonEnglishNamePerson(src, new EnglishNameE(value), nPerson));
            }
        }

        return result;
    }




}
