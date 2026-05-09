package com.lyf.es.controller;

import com.lyf.es.service.LoginServer;
import com.lyf.es.utils.AccessAddressUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {
//    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginServer loginServer;

    /**
     * 登录
     *
     * @return
     */
//    @IpAddr
    @RequestMapping(value = "userlogin", method = {RequestMethod.POST})
    public Object login(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject params) throws Exception {

        // 获取登录ip
        String loginIp = AccessAddressUtil.getIpAddress(request);

        JSONObject json = loginServer.login(params, loginIp);
        String token = json.getString("token");
        String JSESSIONID = request.getSession().getId();
        response.setHeader("token", token);
        response.setHeader("JSESSIONID", JSESSIONID);
        json.put("token", token);
        json.put("JSESSIONID", JSESSIONID);
        return json;
    }
}