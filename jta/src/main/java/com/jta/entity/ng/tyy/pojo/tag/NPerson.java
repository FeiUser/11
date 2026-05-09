package com.jta.entity.ng.tyy.pojo.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class NPerson {
    @Id
    private Integer id;
//    private Integer sid;
    private String facebookId;
//    private String linkedinConnections;
//    private String it;
    private String fullName;
    private String englishName;
//    private String emails;
    private String mobilePhone;
    private String gender;
    private String firstName;
    private String lastName;
    private String jobCompanyName;
    private String jobCompanySize;

    private String locationContinent;
    private String locationName;
    private String linkedinUrl;
    private String linkedinUsername;
    private String jobTitle;
//    private String jobTitleLevels;
//    private String experience;
//    private String phoneNumbers;
//    private String regions;
//    private String skills;
//    private String streetAddresses;
//    private String education;
//    private String profiles;
//    private String languages;
    private String facebookUrl;
    private String hometown;
    private String address;
    private String city;
    private String province;
    private String dist;
    private String userId;
    private String userPwd;
//    private String lastloginStation;
    private String userAmount;
    private String idNumber;
    private String birthDate;
    private String town;
    private String income;
    private String industry;
    private String degrees;
    private String marriage;
    private String nameFacebook;
//    private String pw;
    private String email;
//    private String sourceFrom;
    private String ttUserName;
    private String ttUserScreenName;
    private String ttUserId;
//    private String ttUserHeadimg;
    private String ttUserUrl;
    private Integer mainLand;
}
