package com.example.filter;

import com.example.exception.RateLimitException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 自定义的限流过滤器
 *
 * @author xuan
 * @create 2018-06-06 20:54
 **/
@Component
public class RateLimitFilter extends ZuulFilter {

    private static final RateLimiter limiter = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 限流的优先级应该最高
     * @return
     */
    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        if (!limiter.tryAcquire()) {    //每次尝试获取1个token，获取不到就说明流量太大了
            throw new RateLimitException("请求人数过多，流量被限制");
        }
        return null;
    }
}
