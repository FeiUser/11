package com.lyf.es.aspect;

import com.lyf.es.annotations.IpAddr;
import com.lyf.es.pojo.result.HttpResult;
import com.lyf.es.utils.AccessAddressUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Autowired
    private HttpServletRequest request;

    @Value("${ipaddr}")
    private List<String> ipaddr;

    /**
     * 定义一个切入点.
     * 解释下：
     * <p>
     * ~ 第一个 * 代表任意修饰符及任意返回值.
     * ~ 第二个 * 任意包名
     * ~ 第三个 * 代表任意方法.
     * ~ 第四个 * 定义在web包或者子包
     * ~ 第五个 * 任意方法
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(public * com.lyf.es.controller..*.*(..))")
    public void webLog() {
    }

    @Before("@annotation(ipAddr)")
    public void doBefore(IpAddr ipAddr) {
        String loginIp = AccessAddressUtil.getIpAddress(request);
        System.out.println(loginIp);
        if (!ipaddr.contains(loginIp)) {
            throw new RuntimeException("无权限访问！");
        }
        String parameter = request.getQueryString();
        // s = isiTeam + bhTeam + 时间戳
        String s = new String(Base64.decodeBase64(parameter));
        if (StringUtils.isNotEmpty(s)) {
            long currentTimeMillis = System.currentTimeMillis();
            long requestTimeMillis = Long.parseLong(s.substring(13));
            long subTime = currentTimeMillis - requestTimeMillis;
            if (!"isiTeam".equals(s.substring(0, 7)) || !"bhTeam".equals(s.substring(7, 13))) {
                throw new RuntimeException("无权限访问！");
            }
        }
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("请求URL : " + request.getRequestURL().toString());
        logger.info("请求METHOD : " + request.getMethod());
        logger.info("请求地址IP : " + request.getRemoteAddr());
        logger.info("请求参数ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数方法一：
        /*Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            System.out.println(paraName+": "+request.getParameter(paraName));
        }*/
    }


    @AfterReturning("webLog()")
    public void  doAfterReturning(JoinPoint joinPoint){
        // 处理完请求，返回内容
        logger.info("当前请求耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
    }

    @Around("webLog()")
    public Object resultsFiledFilter(ProceedingJoinPoint pjd){
        try {
            Object controllerResults = pjd.proceed();
            return HttpResult.success(controllerResults);

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error("请求方法异常-------->"+throwable.getMessage());
            return HttpResult.error("程序异常，请联系开发人员！错误说明："+throwable.getMessage());
        }
    }
}