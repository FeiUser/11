package com.jta.entity.ng.tyy.dao;


import com.jta.entity.ng.tyy.pojo.edge.*;
import com.jta.entity.ng.tyy.pojo.result.PersonEnglishNamePerson;
import com.jta.entity.ng.tyy.pojo.result.PersonFullNamePerson;
import com.jta.entity.ng.tyy.pojo.tag.NPerson;
import com.vesoft.nebula.client.graph.data.ResultSet;
import org.nebula.contrib.ngbatis.proxy.NebulaDaoBasic;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NPersonDao extends NebulaDaoBasic<NPerson, Integer> {

    //    List<Map> zidingyi();
//    List<PlayerFlowPlayer> zidingyi2();
    NPerson findById(@Param("id0") Integer id0);

    ResultSet findIdsByFullNameEPage(@Param("from") Integer from, @Param("pageSize") Integer pageSize);

    ResultSet findIdsByEnglishNameEPage(@Param("from") Integer from, @Param("pageSize") Integer pageSize);

    ResultSet findIdsByIndustryEPage(@Param("from") Integer from, @Param("pageSize") Integer pageSize);

    ResultSet findIdsByIdNumberIdEPage(@Param("from") Integer from, @Param("pageSize") Integer pageSize);

    ResultSet findIdsByFacebookIdEPage(@Param("from") Integer from, @Param("pageSize") Integer pageSize);

    ResultSet findIdsByEmailIdEPage(@Param("from") Integer from, @Param("pageSize") Integer pageSize);

    ResultSet findIdsByJobCompanyIdEPage(@Param("from") Integer from, @Param("pageSize") Integer pageSize);

    ResultSet findIdsByTwitterUrlIdEPage(@Param("from") Integer from, @Param("pageSize") Integer pageSize);

    ResultSet findIdsByMobilePhoneIdEPage(@Param("from") Integer from, @Param("pageSize") Integer pageSize);



    List<NPerson> findPersonPage(@Param("from") Integer from, @Param("pageSize") Integer pageSize);

    List<NPerson> findPersonByFullNamePage(@Param("fullName") String fullName, @Param("from") Integer from, @Param("pageSize") Integer pageSize);

    List<NPerson> findPersonByEnglishNamePage(@Param("englishName") String englishName, @Param("from") Integer from, @Param("pageSize") Integer pageSize);

    List<NPerson> findPersonByIndustryPage(@Param("industry") String industry, @Param("from") Integer from, @Param("pageSize") Integer pageSize);

    List<NPerson> findPersonByIdNumberPage(@Param("idNumber") String idNumber, @Param("from") Integer from, @Param("pageSize") Integer pageSize);

    List<NPerson> findPersonByFacebookIdPage(@Param("facebookId") String facebookId, @Param("from") Integer from, @Param("pageSize") Integer pageSize);

    List<NPerson> findPersonByTwitterUrlPage(@Param("twitterUrl") String twitterUrl, @Param("from") Integer from, @Param("pageSize") Integer pageSize);

    List<NPerson> findPersonByMobilePhonePage(@Param("mobilePhone") String mobilePhone, @Param("from") Integer from, @Param("pageSize") Integer pageSize);

    List<NPerson> findPersonByJobCompanyPage(@Param("jobCompany") String jobCompany, @Param("from") Integer from, @Param("pageSize") Integer pageSize);

    List<NPerson> findPersonByEmailPage(@Param("email") String email, @Param("from") Integer from, @Param("pageSize") Integer pageSize);



    List<NPerson> findPersonByFullNameLimitN(@Param("fullName") String fullName, @Param("n") Integer n);

    List<NPerson> findPersonByEnglishNameLimitN(@Param("englishName") String englishName, @Param("n") Integer n);

    List<NPerson> findPersonByIndustryLimitN(@Param("industry") String industry, @Param("n") Integer n);

    List<NPerson> findPersonByIdNumberLimitN(@Param("idNumber") String idNumber, @Param("n") Integer n);

    List<NPerson> findPersonByFacebookIdLimitN(@Param("facebookId") String facebookId, @Param("n") Integer n);

    List<NPerson> findPersonByMobilePhoneLimitN(@Param("mobilePhone") String mobilePhone, @Param("n") Integer n);

    List<NPerson> findPersonByTwitterUrlLimitN(@Param("twitterUrl") String twitterUrl, @Param("n") Integer n);

    List<NPerson> findPersonByEmailLimitN(@Param("email") String email, @Param("n") Integer n);

    List<NPerson> findPersonByJobCompanyLimitN(@Param("jobCompany") String jobCompany, @Param("n") Integer n);



    void insertNameEdgeOne2More(@Param("id0") Integer id0, @Param("n") FullNameE fullNameE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertEnglishNameEdgeOne2More(@Param("id0") Integer id0, @Param("n") EnglishNameE englishNameE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertFacebookEdgeOne2More(@Param("id0") Integer id0, @Param("n") FacebookE facebookE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertMobilePhoneEdgeOne2More(@Param("id0") Integer id0, @Param("n") MobilePhoneE mobilePhoneE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertEmailEdgeOne2More(@Param("id0") Integer id0, @Param("n") EmailE emailE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertJobCompanyEdgeOne2More(@Param("id0") Integer id0, @Param("n") JobCompanyE jobCompanyE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertTwitterUrlEdgeOne2More(@Param("id0") Integer id0, @Param("n") TwitterUrlE twitterUrlE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    List<PersonEnglishNamePerson> findEdgePersonEnglishNamePerson(@Param("id0") Integer id0);

    List<PersonFullNamePerson> findEdgePersonFullNamePerson(@Param("id0") Integer id);



    void insertFullNameIdE(@Param("id0") Integer id, @Param("n") FullNameIdE fullNameIdE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertEnglishNameIdE(@Param("id0") Integer id, @Param("n") EnglishNameIdE englishNameIdE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertIndustryEdgeOne2More(@Param("id0") Integer id0, @Param("n") IndustryE industryE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertIdNumberIdE(@Param("id0") Integer id, @Param("n")  IdNumberIdE idNumberIdE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertMobilePhoneIdE(@Param("id0") Integer id, @Param("n")  MobilePhoneIdE mobilePhoneIdE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertEmailIdE(@Param("id0") Integer id, @Param("n")  EmailIdE emailIdE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertJobCompanyIdE(@Param("id0") Integer id, @Param("n")  JobCompanyIdE jobCompanyIdE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertFacebookIdE(@Param("id0") Integer id, @Param("n")  FacebookIdE facebookIdE, @Param("nPersonIdList") List<Integer> nPersonIdList);

    void insertTwitterUrlIdE(@Param("id0") Integer id, @Param("n")  TwitterUrlIdE twitterUrlIdE, @Param("nPersonIdList") List<Integer> nPersonIdList);


    List<Integer> getDestIdByFullName(@Param("id0") String fullName);

    ResultSet showStatus();

    ResultSet createEdgeTest(@Param("s1") String s1, @Param("s2") String s2);

    ResultSet createEdgeIndexTest();

    ResultSet dropEdgeTest();
}