package com.lyf.es.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    private static String getNumber(String string, String regex) {
//        String regex = "\\d+";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(string);

        StringBuilder builder = new StringBuilder();
        while (matcher.find()) {
            builder.append(matcher.group(0));
            break;
        }
        return builder.toString();
    }



    public static void main(String[] args) {
//        String s = "+1646727(88)43";
//        System.out.println(s);
//        String number = getNumber(s);
//        System.out.println(number);

//        Scanner sc=new Scanner(System.in);
//        while(true) {
//            System.out.println("请输入任意字符串进行判断:");
//            String str=sc.nextLine();
//            if(!str.matches("[\\da-zA-Z]+"))
//                System.out.println("错误");
//            else
//                System.out.println("正确");
//        }

//        String s = onlyNumber("71:新竹縣竹北市縣政一街46巷3號:30242:035518552:035518553:0:市話用戶:男:0:劉源鴻:0");
//        String s = onlyNumber("71:竹北市縣政一街46巷3號:30242:035518552:035518553:0:市話用戶:男:0:劉源鴻:0");
//        String s = onlyNumber("1969/10/02:36:新竹縣竹北市國華街40號5樓之2:30243:0:市話用戶:女:0:蘇淑華:0");
//        String s = onlyNumber("42:桃園縣蘆竹鄉中山路59之1號12樓之1:33842:0:市話用戶:男:0:廖大岳:0");
//        String s = onlyNumber("1975/07/23:0936647158:30000:新竹縣新竹市新竹路397號:CURIMO:新竹縣新竹市新竹路397號:新竹縣新竹市新竹路397號:030000:300");

        String mobilePhone = null;
        String cityAndTown = "雲林縣斗南鎮福德街164巷5號";
        int i = Integer.parseInt(mobilePhone);
        System.out.println(i);
//        if (mobilePhone.matches("\\d+")) {
//            int i = Integer.parseInt(mobilePhone);
//            System.out.println(i);
//        } else {
//            System.out.println(mobilePhone);
//        }
//        String phone = dealMobilePhoneNew(mobilePhone);
//        phone = dealTWPhoneAreaNumberByAddress(phone, cityAndTown);
//        System.out.println(phone);

//        String number = getNumber("");
//        System.out.println(number);

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("mobile_phone", null);
//        String phone = null != jsonObject.getString("mobile_phone") ? jsonObject.getString("mobile_phone") : "";
//        String s = phone.toString();
//        System.out.println(s);

//        1927/02/11
//        String string = "1927/02/11";
//        String string = "1956/03/08:48:桃園縣龜山鄉宏慶街71號3樓:羅炎貴,29031439,A122294178,1956/01/01:48:桃園縣龜山鄉宏慶街71號3樓";
//        String regex = "\\d{4}/\\d{1,2}/\\d{1,2}";
//        Pattern p = Pattern.compile(regex);
//        Matcher matcher = p.matcher(string);
//        if (matcher.find()) {
//            String group = matcher.group();
//            System.out.println(group);
//        }

//        JSONObject xianShi = getXianShi("38:彰化縣員林鎮新生路127號:51045:err:8351000:8351300:0:市話用戶:男:0:黃錫水:0");
//        System.out.println(xianShi.toJSONString());
    }

    private static JSONObject getXianShi(String address) {
        JSONObject json = new JSONObject();
        String[] split = address.split(":");
        String cityAndTown = "";
        for (String s : split) {
            if (s.contains("縣") || s.contains("市")) {
                cityAndTown = s;
                break;
            }
        }
        if (cityAndTown.contains("市話用戶")) {
            cityAndTown = "";
        }
        json.put("address", cityAndTown);
        String xian = "";
        int xianIndex = cityAndTown.indexOf("縣");
        String shi = "";
        int shiIndex = cityAndTown.indexOf("市");
        // 存在县、存在市、县再市前边
        if (-1 != xianIndex && -1 != shiIndex && xianIndex < shiIndex) {
            xian = cityAndTown.substring(0, xianIndex + 1);
            if (-1 != shiIndex) {
                shi = cityAndTown.substring(xianIndex + 1, shiIndex + 1);
            }
        } else if (-1 != xianIndex && -1 == shiIndex) {
            // 只存在县，不存在市
            xian = cityAndTown.substring(0, xianIndex + 1);
        } else if (-1 != shiIndex && (-1 == xianIndex || xianIndex > shiIndex)) {
            // 只存在市，不存在县
            shi = cityAndTown.substring(0, shiIndex + 1);
        }
        json.put("city", shi);
        json.put("town", xian);
        return json;
    }
    public static String dealMobilePhone(String mobilePhone) {
        String[] inLandArray = {"13", "14", "15", "16", "17", "18", "19"};
        List<String> inLandList = Arrays.asList(inLandArray);
        mobilePhone = getNumber(mobilePhone, "\\d+");

        if (StringUtils.isNotEmpty(mobilePhone)) {
            int length = mobilePhone.length();
            int i = mobilePhone.indexOf("886");
            // 处理大陆电话
            if (length == 11 && inLandList.contains(mobilePhone.substring(0, 2))) {
                mobilePhone = "86" + mobilePhone;
            } else if (!mobilePhone.contains("886") || (i > 0 && 0 != Long.parseLong(mobilePhone.substring(0, i)))){
                // 处理台湾电话
                mobilePhone = "886" + mobilePhone;
            }
        }
        return mobilePhone;
    }

    private static String onlyNumber(String address) {
        String[] split = address.split(":");
        String cityAndTown = "";
        for (String s : split) {
            if (s.contains("縣") || s.contains("市")) {
                cityAndTown = s;
                break;
            }
        }
        return cityAndTown;
    }

    public static String dealMobilePhoneNew(String mobilePhone) {
        String[] inLandArray = {"13", "14", "15", "16", "17", "18", "19"};
        List<String> inLandList = Arrays.asList(inLandArray);
        mobilePhone = getNumber(mobilePhone, "\\d+");
        mobilePhone = deleteBeforeStringZero(mobilePhone);
        if (StringUtils.isNotEmpty(mobilePhone)) {
            int length = mobilePhone.length();
            int indexOf886 = mobilePhone.indexOf("886");
            // 处理大陆电话
            if (length == 11 && inLandList.contains(mobilePhone.substring(0, 2))) {
                mobilePhone = "86" + mobilePhone;
            } else if (length == 12 && "44".equals(mobilePhone.substring(0, 2))) {  // 处理linkedIn英国phoneNumber
                return mobilePhone;
            } else if (mobilePhone.contains("886") && indexOf886 == 0) {// 处理台湾phoneNumber    //源数据还有886的 8860123
                if ("0".equals(mobilePhone.substring(3, 4))) {
                    // 去掉8860123中间的0
                    mobilePhone = mobilePhone.substring(0, 3) + mobilePhone.substring(indexOf886 + 2);
                }
            } else if (!mobilePhone.contains("886")
                    || (indexOf886 > 0 && 0 != Long.parseLong(mobilePhone.substring(0, indexOf886)))) {//特殊 不包含886 或 者包含886但886不是开头的且前边不是0  （123886）
                mobilePhone = "886" + mobilePhone;
            }
        }
        return mobilePhone;
    }

    /**
     * 00886  -> 886
     * @param string
     * @return
     */
    public static String deleteBeforeStringZero(String string) {
        int beforeStringNotZeroIndex = -1;
        int length = string.length();
        for (int i = 0; i < length; i++) {
            int parseInt = Integer.parseInt(String.valueOf(string.charAt(i)));
            if (parseInt > 0) {
                beforeStringNotZeroIndex = i;
                break;
            }
        }
        if (beforeStringNotZeroIndex != -1) {
            string = string.substring(beforeStringNotZeroIndex, length);
        }
        return string;
    }

    private static String dealTWPhoneAreaNumberByAddress(String phone, String address) {
        if (StringUtils.isNotEmpty(address)) {
            if (phone.contains("886")) {   // 886479
                int indexOf886 = phone.indexOf("886");
                if (indexOf886 == 0) {
                    int parseInt = Integer.parseInt(phone.substring(3, 4));
                    if (parseInt != 9 && parseInt != 0) {
                        String areaNumber = getPhoneAreaNumber(address);
                        if (parseInt != Integer.parseInt(areaNumber.substring(1))) {    // 8863583070   区对应的数字与第一位相同，则不需要加03，只加0
                            phone = phone.substring(0, 3) + areaNumber + phone.substring(3);
                        } else {
                            phone = phone.substring(0, 3) + "0" + phone.substring(3);
                        }
                    }
                }
            }
        }
        return phone;
    }

    private static String getPhoneAreaNumber(String address) {
        List<String> list02 = new ArrayList<>();
        list02.add("臺北");
        list02.add("台北");
        list02.add("新北");
        list02.add("隆基");
        for (String s : list02) {
            if (address.contains(s)) {
                return "02";
            }
        }

        List<String> list04 = new ArrayList<>();
        list04.add("台中");
        list04.add("卓兰");
        list04.add("卓蘭");
        list04.add("彰化");
        list04.add("南投");
        for (String s : list04) {
            if (address.contains(s)) {
                return "04";
            }
        }

        List<String> list03 = new ArrayList<>();
        list03.add("桃園");
        list03.add("桃园");
        list03.add("新竹");
        list03.add("花莲");
        list03.add("花蓮");
        list03.add("宜兰");
        list03.add("宜蘭");
        list03.add("苗栗");
        for (String s : list03) {
            if (address.contains(s)) {
                return "03";
            }
        }

        List<String> list05 = new ArrayList<>();
        list05.add("嘉义");
        list05.add("嘉義");
        list05.add("云林");
        list05.add("雲林");
        for (String s : list05) {
            if (address.contains(s)) {
                return "05";
            }
        }

        List<String> list06 = new ArrayList<>();
        list06.add("台南");
        list06.add("澎湖");
        for (String s : list06) {
            if (address.contains(s)) {
                return "06";
            }
        }

        List<String> list07 = new ArrayList<>();
        list07.add("高雄");
        for (String s : list07) {
            if (address.contains(s)) {
                return "07";
            }
        }

        List<String> list08 = new ArrayList<>();
        list08.add("屏东");
        list08.add("屏東");
        list08.add("台东");
        list08.add("台東");
        list08.add("金门");
        list08.add("金門");
        list08.add("乌坵");
        list08.add("烏丘");
        list08.add("媽祖");
        list08.add("马祖");
        for (String s : list08) {
            if (address.contains(s)) {
                return "08";
            }
        }
        return "";
    }
}
