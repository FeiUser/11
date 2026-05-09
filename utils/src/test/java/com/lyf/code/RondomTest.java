package com.lyf.code;

import org.junit.Test;

import java.util.*;

public class RondomTest {
    class Person {
        private int id;
        private String name;

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public Person (int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
    @Test
    public void test01() {
        List<Person> peoples = new ArrayList<>();
        Person a = new Person(1, "a");
        peoples.add(a);
        Person b = new Person(2, "b");
        peoples.add(b);


        for (Person people : peoples) {
            if (people.getId() == 1) {
                people.setName("c");
            }
        }

        for (Person people : peoples) {
            System.out.println(people.toString());
        }
    }

    @Test
    public void test02() {
        Map< String,Object> parms = new HashMap<>();
        parms.put("id",1);
        parms.put("fullName",1);
        parms.put("englishName",1);
        parms.put("mobilePhone",1);
        parms.put("gender",1);
        parms.put("firstName",1);
        parms.put("lastName",1);
        parms.put("jobCompanyName",1);
        parms.put("facebookId",1);
        parms.put("jobCompanySize",1);
        parms.put("locationName",1);
        parms.put("linkedinUrl",1);
        parms.put("linkedinUsername",1);
        parms.put("jobTitle",1);
        parms.put("facebookUrl",1);
        parms.put("hometown",1);
        parms.put("address",1);
        parms.put("city",1);
        parms.put("province",1);
        parms.put("dist",1);
        parms.put("userId",1);
        parms.put("userPwd",1);
        parms.put("userAmount",1);
        parms.put("idNumber",1);
        parms.put("birthDate",1);
        parms.put("town",1);
        parms.put("income",1);
        parms.put("industry",1);
        parms.put("degrees",1);
        parms.put("marriage",1);
        parms.put("nameFacebook",1);
        parms.put("email",1);
        parms.put("ttUserName",1);
        parms.put("ttUserScreenName",1);
        parms.put("ttUserId",1);
        parms.put("ttUserUrl",1);
        parms.put("mainLand",1);

//    fullName` string,
//    englishName` string,
//    mobilePhone` string,
//    gender` string,
//    firstName` string,
//    lastName` string,
//    jobCompanyName` string,
//    facebookId` string,
//    jobCompanySize` string,
//    locationContinent` string,
//    locationName` string,
//    linkedinUrl` string,
//    linkedinUsername` string,
//    jobTitle` string,
//    facebookUrl` string,
//    hometown` string,
//    address` string,
//    city` string,
//    province` string,
//    dist` string,
//    userId` string,
//    userPwd` string,
//    userAmount` string,
//    idNumber` string,
//    birthDate` string,
//    town` string,
//    income` string,
//    industry` string,
//    degrees` string,
//    marriage` string,
//    nameFacebook` string,
//    email` string,
//    ttUserName` string,
//    ttUserScreenName` string,
//    ttUserId` string,
//    ttUserUrl` string,
//    mainLand` int
    }

    @Test
    public void test03() {
        String s = "\"summary\":\"Computer Science I * Computer Science II\\\"]";
        String s2 = "对于滑动窗口协议，若分组序号采用 3 比特编号，发送窗口大小为 5,则接受窗口最大为多少";
        String s15 = "主机甲采用停止-等待协议向主机乙发送数据，数据传输速率是 3kb/s,单向传播时延是 200ms，忽略确认帧的传输时延。当信道利用率等于 40%时，数据帧的长度 400bit";
        String s16 = "主机甲通过 128kb/s 卫星链路，采用滑动窗口协议向主机乙发送数据，链路单向传播时延为 250ms，帧长为 1000 字节。不考虑确认的开销，为使链路利用率不小于80%，帧序号的比特数至少是 5";
        System.out.println(s);
        s = s.replaceAll("\\\\\"]\"", "\"");
        System.out.println(s);

    }

    @Test
    public void test04() {
        String s = "https\\\\://twitter.com/haru46300093";
        String replaceAll = s.replaceAll("\\/", "\\\\\\\\/");
        System.out.println(replaceAll);
    }

    @Test
    public void test05() {
        String s = "技嘉科技|fenner precision|";
        System.out.println(s.split("\\|")[0]);

        double percent = 80.21123;

        System.out.println(String.format("%.2f%%", percent));
    }

    @Test
    public void test06() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list);
    }

    @Test
    public void testBitSet() {
        BitSet bitSet = new BitSet();
        bitSet.set(0, true);
        bitSet.set(1, true);
        bitSet.set(2, false);
        bitSet.set(3, true);
        bitSet.set(103, true);

        int cardinality = bitSet.cardinality();
        System.out.println(cardinality);
//        for (int i = 0; i < bitSet.size(); i++) {
//            System.out.println(bitSet);
//        }
        System.out.println(32>>5);
    }

}
// uyghur,新疆 伊斯兰,新疆 宗教迫害,新疆棉花,教育营,集中营,强制劳动,世界维吾尔代表大会,维吾尔人权,伊利夏提,新疆,马兴瑞,陈全国
