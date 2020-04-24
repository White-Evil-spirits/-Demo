package com.zys.boot_jeep.shiro;

import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.UUID;

/**
 * @program:
 * @author:
 * @create: 2020-01-04 10:
 **/
@Configuration
public class TokenWebSessionManager extends DefaultWebSessionManager {

    private static final String TOKEN_HEADER = "TOKEN" ;

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        System.out.println("执行session管理器>>>");
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        System.out.println(httpServletRequest.getRequestURI());
        //从头里面得到请求TOKEN 如果不存在就生成一个
        String header = WebUtils.toHttp(request).getHeader(TOKEN_HEADER);
        System.out.println(header+"---TokenWebSessionManager");
        if (StringUtils.hasText(header)) {
            return header;
        }
        String str = UUID.randomUUID().toString();
        System.out.println(str+"-----------------------");
        return str;
    }
}
