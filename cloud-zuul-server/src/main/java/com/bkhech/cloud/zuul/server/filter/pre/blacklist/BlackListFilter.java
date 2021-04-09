package com.bkhech.cloud.zuul.server.filter.pre.blacklist;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * ip黑名单过滤器
 * @author guowm
 * @date 2021/3/18
 */
@Component
public class BlackListFilter extends ZuulFilter {

    private static List<String> blackList;

    static {
        // 模拟黑名单
        blackList = Arrays.asList("192.168.71.23");
    }


    /**
     * filter 类型
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * filter 排序 越小越先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否启用 filter
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 业务处理
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 获取上下文 context
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 获取 request
        HttpServletRequest request = currentContext.getRequest();
        // 客户端地址
        String remoteAddr = request.getRemoteAddr();

        if (blackList.contains(remoteAddr)) {
            // 请求就此结束，不向下游请求
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseBody("非法请求");
            currentContext.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            currentContext.getResponse().setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        }

        return null;
    }
}
