package org.test.mybatis.entity.ds1;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer sex;
    private String sexString;
    private Date createTime;
}
