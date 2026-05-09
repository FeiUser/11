package com.lyf.es.model.po.tyy.rep;

import com.lyf.es.model.po.tyy.tab.SysUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysUserInfoRepository extends JpaRepository<SysUserInfo,Integer>, JpaSpecificationExecutor<SysUserInfo> {
    SysUserInfo findByUsernameAndPassword(String name, String password);

    SysUserInfo findByUsername(String name);

}