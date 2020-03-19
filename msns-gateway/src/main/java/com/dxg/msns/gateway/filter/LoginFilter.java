package com.dxg.msns.gateway.filter;

import com.dxg.msns.auth.utils.CookieUtils;
import com.dxg.msns.auth.utils.JwtUtils;
import com.dxg.msns.gateway.config.FilterProperties;
import com.dxg.msns.gateway.config.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class LoginFilter extends ZuulFilter {
    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private FilterProperties filterProperties;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 5;
    }

    @Override
    public boolean shouldFilter() {
        // 获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest request = currentContext.getRequest();
        // 获取路径
        String requestURI = request.getRequestURI();
        // 判断白名单
        // 遍历允许访问的路径
        for (String path : this.filterProperties.getAllowPaths()) {
            // 然后判断是否是符合
            if(requestURI.startsWith(path)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());
        try {
            JwtUtils.getInfoFromToken(token,this.jwtProperties.getPublicKey());
        } catch (Exception e) {
            // 校验出现异常，返回403
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
        }
        return null;
    }
}
