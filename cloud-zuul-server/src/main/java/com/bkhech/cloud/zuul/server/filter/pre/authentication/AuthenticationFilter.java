package com.bkhech.cloud.zuul.server.filter.pre.authentication;

import com.bkhech.cloud.zuul.server.authentication.jwt.AuthService;
import com.bkhech.cloud.zuul.server.authentication.jwt.config.JwtConfig;
import com.bkhech.cloud.zuul.server.filter.pre.blacklist.BlackListFilter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 认证过滤器
 *
 * @author guowm
 * @date 2021/3/22
 */
@Slf4j
@Component
public class AuthenticationFilter extends ZuulFilter {

    private final AuthService authService;

    private final JwtConfig jwtConfig;

    private final BlackListFilter blackListFilter;

    public AuthenticationFilter(AuthService authService, JwtConfig jwtConfig, BlackListFilter blackListFilter) {
        this.authService = authService;
        this.jwtConfig = jwtConfig;
        this.blackListFilter = blackListFilter;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 在黑名单过滤器之后执行
     * @see BlackListFilter
     * @return
     */
    @Override
    public int filterOrder() {
        return blackListFilter.filterOrder() + 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = getActualUri(request);

        Set<String> skipAuthUrls = jwtConfig.getSkipAuthUrls();
        skipAuthUrls.add(jwtConfig.getLoginUrl());

        // 跳过不需要验证的路径
        if (skipAuthUrls.contains(requestURI)) {
            return false;
        }
        return true;
    }

    private String getActualUri(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (!requestURI.startsWith("/")) {
            requestURI = "/" + requestURI;
        }
        String[] requestUris = requestURI.split("/", 3);
        return "/" + requestUris[2];
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        // 获取 jwtToken
        String jwtToken = request.getHeader("Authorization");

        // 没有token
        if (StringUtils.isEmpty(jwtToken)) {
            authError(currentContext, "need to auth");
        } else {
            // 有token
            try {
                authService.checkToken(jwtToken);
            } catch (ExpiredJwtException e) {
                String errorMessage;
                if (e.getMessage().contains("Allowed clock skew")) {
                    errorMessage = "认证过期";
                } else {
                    errorMessage = "认证失败";
                }
                authError(currentContext, errorMessage);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                authError(currentContext, "认证失败");
            }
        }

        return null;
    }

    /**
     * 认证错误输出
     *
     * @param currentContext 响应上下文
     * @param message        错误信息
     * @return
     */
    private void authError(RequestContext currentContext, String message) {
        // 请求就此阶段，不向下游请求
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseBody(message);
        currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        currentContext.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    }
}
