package com.example.filter;

import com.example.constant.CookieConstant;
import com.example.util.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * 针对某些接口进行过滤，本例中，/user/order/finish只能卖家访问，
 * 而卖家的标志是：登录后cookie中有"token"=uuid，根据uuid能从redis中查出openid。否则就过滤掉
 *
 * @author xuan
 * @create 2018-06-09 20:23
 **/
@Component
public class SellerFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if ("/zuul/user/user/order/finish".equals(request.getRequestURI())) {
            String token = CookieUtil.get(request, CookieConstant.TOKEN);
            if (token == null || redisTemplate.opsForValue().get(token) == null) {
                requestContext.setSendZuulResponse(false);  //过滤掉，不放行
                requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            }
        }
        return null;
    }
}
