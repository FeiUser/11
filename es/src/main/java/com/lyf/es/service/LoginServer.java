package com.lyf.es.service;

import com.lyf.es.model.po.tyy.rep.SysUserInfoRepository;
import com.lyf.es.model.po.tyy.tab.SysUserInfo;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServer{
    @Autowired
    private SysUserInfoRepository sysUserInfoRepository;

    private final static Logger logger = LoggerFactory.getLogger(LoginServer.class);


    public JSONObject login(JSONObject params, String loginIp) throws Exception {
        JSONObject result = new JSONObject();

        if (!params.containsKey("username") || StringUtils.isEmpty(params.getString("username")) ||
                !params.containsKey("password") || StringUtils.isEmpty(params.getString("password"))) {
            throw new Exception("用户名或密码不能为空！");
        }

        String username = params.getString("username").trim().replaceAll("\r", "").replaceAll("\n", "");
        String password = params.getString("password").trim().replaceAll("\r", "").replaceAll("\n", "");
        SysUserInfo userInfo = sysUserInfoRepository.findByUsername(username);
//        if (null ==userInfo) {
//            throw new Exception("无此用户，请检查用户名是否正确！");
//        }
//
//        logger.info("用户[{}]正在尝试登录，来源IP：{} !", username, loginIp);
//
//        String s = new String(Base64.decodeBase64(password));
//        SysUserInfo user = sysUserInfoRepository.findByUsernameAndPassword(username, Md5Util.encodePassword(s));
//        if (null != user) {
////            JSONArray menu = menu(user);
////            result.put("menu", menu);
//            result.put("user", user);
//            String token = JWT.createJWT(UUID.randomUUID().toString(), result.getString("user"), "7786df7fc3a34e26a61c034d5ec8245d", 31 * 24 * 60 * 60 * 1000);
//            userInfo.setPassword(null);
//            result.put("user", user);
//            result.put("token", token);
//        } else {
//            throw new Exception("密码错误");
//        }
        return result;

    }
}
