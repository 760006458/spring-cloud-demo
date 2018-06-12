package com.example.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 自定的token过滤器，如果用户的请求参数中没有token，则进行过滤
 *
 * @author xuan
 * @create 2018-06-06 20:23
 **/
@Component
public class TokenFilter extends ZuulFilter {

    /**
     * 过滤器类型：pre表示前置过滤器
     *
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 过滤器顺序：越小越靠前
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
//            requestContext.setSendZuulResponse(false);  //令zuul过滤该请求，不对其进行路由
//            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());  //401权限不足
        }
        return null;
    }
}
