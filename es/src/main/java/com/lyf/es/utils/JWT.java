package com.lyf.es.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JWT {

    public static String createJWT(String id, String subject, String username, long ttlMillis) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        Date now = new Date(nowMillis);
        SecretKey key = generalKey(username);//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
//                .setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(id)                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now)           //iat: jwt的签发时间
                .setSubject(subject)        //sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, key);//设置签名使用的签名算法和签名使用的秘钥
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);     //设置过期时间
        }
        return builder.compact();           //就开始压缩为xxxxxxxxxxxxxx.xxxxxxxxxxxxxxx.xxxxxxxxxxxxx这样的jwt
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    private static SecretKey generalKey(String username) {
//        String stringKey = Constant.JWT_SECRET;//本地配置文件中加密的密文7786df7fc3a34e26a61c034d5ec8245d
        byte[] encodedKey = Base64.decodeBase64(username);//本地的密码解码[B@152f6e2
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");// 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。（后面的文章中马上回推出讲解Java加密和解密的一些算法）
        return key;
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt, String username) throws Exception {
        SecretKey key = generalKey(username);  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)//设置签名的秘钥
                .parseClaimsJws(jwt).getBody();//设置需要解析的jwt
        return claims;
    }

    public static void main(String[] args) throws Exception {
//        JWT util = new JWT();
////        String ab=util.createJWT("jwt", "{id:100,name:xiaohong}", 60000);
////        System.out.println(ab);
//        //eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiJ7aWQ6MTAwLG5hbWU6eGlhb2hvbmd9IiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiJEQVNEQTEyMSIsImV4cCI6MTUxNzgzNTE0NiwiaWF0IjoxNTE3ODM1MDg2LCJqdGkiOiJqd3QifQ.ncVrqdXeiCfrB9v6BulDRWUDDdROB7f-_Hg5N0po980
////        String jwt="eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJEU1NGQVdEV0FEQVMuLi4iLCJzdWIiOiJ7aWQ6MTAwLG5hbWU6eGlhb2hvbmd9IiwidXNlcl9uYW1lIjoiYWRtaW4iLCJuaWNrX25hbWUiOiJEQVNEQTEyMSIsImV4cCI6MTUxNzgzNTEwOSwiaWF0IjoxNTE3ODM1MDQ5LCJqdGkiOiJqd3QifQ.G_ovXAVTlB4WcyD693VxRRjOxa4W5Z-fklOp_iHj3Fg";
//        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjNTlhNjVlOS1iZmY2LTQ4NDgtYTc4OC01MzljZjg2NTkzOGEiLCJpYXQiOjE1MzU2MTYxMTEsInN1YiI6IntcImFnZW50SWRcIjotMSxcImRlcElkXCI6MzE5LFwiaWRcIjo1NjAsXCJyZWFsTmFtZVwiOlwiXCIsXCJzdGF0dXNcIjoxLFwidGVsZXBob25lXCI6XCJcIixcInVzZXJuYW1lXCI6XCJ6a3l1c2VyXCIsXCJldmVudF9tYXhcIjoxMCxcImV2ZW50X2t3c19tYXhcIjo1MDB9IiwiZXhwIjoxNTM1NzAyNTExfQ.iwdpDfS79gls9T1AxQUK9-GZ7opCTg5BSjbL9hk7h0w";
//        Claims c = util.parseJWT(jwt, "zkyuser");//注意：如果jwt已经过期了，这里会抛出jwt过期异常。
//        System.out.println(c.getId());//jwt
//        System.out.println(c.getIssuedAt());//Mon Feb 05 20:50:49 CST 2018
//        System.out.println(c.getSubject());//{id:100,name:xiaohong}
//        System.out.println(c.getIssuer());//null
//        System.out.println(c.get("uid", String.class));//DSSFAWDWADAS...
//        System.out.println(c.get("user_name", String.class));//DSSFAWDWADAS...
//        System.out.println(c.get("nick_name", String.class));//DSSFAWDWADAS...

//        Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzZmQ5YWVlZi05NDZjLTQ2ZDQtODMxNC05Y2Q1Yzg0ZmZkMzkiLCJpYXQiOjE2NDY5MDA2NjIsInN1YiI6IntcImlkXCI6OTQzLFwiZGVwSWRcIjo1ODIsXCJpbnN0SWRcIjoyODIsXCJzdXBlclZpZXdcIjowLFwidXNlcm5hbWVcIjpcIm1pbmd5dWFuXCIsXCJ1c2VyVHlwZVwiOjAsXCJzaG93U2hhcmVkRmxhZ1wiOjAsXCJ1a2V5SWRcIjpcIjAwMDAwMFwifSIsImV4cCI6MTY0Njk4NzA2Mn0.kxKBlJCYFL_78EtdmM7BlU0PNiwGHGFKflBs18R8z_w", Constant.JWT_SECRET);
//        System.out.println(claims);

    }
}