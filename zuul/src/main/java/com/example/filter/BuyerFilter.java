package com.example.filter;

import com.example.constant.CookieConstant;
import com.example.util.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

/**
 * 针对/zuul/user/user/order/create接口进行的过滤，只有买家才有资格访问。
 * 买家cookie中有： "openid":openid
 * 注：拦截路径一般是配置到数据库的，所以应该从数据库查询，但这样又污染了边界，网关不该耦合数据库，
 * 用户登录信息的查询应该调用user服务，而每次user服务都查询数据库，压力比较大，所以最后的方法就是借助公共组件redis，
 * 当用户登录相关信息变动时，发一个消息出来，网关监听消息，把消息记录到redis
 *
 * @author xuan
 * @create 2018-06-09 21:46
 **/
@Component
public class BuyerFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤器的order排序可以重复，不会冲突，都会经历，只不过谁先谁后就不好确定了
     * @return
     */
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
        if ("/zuul/user/user/order/create".equals(request.getRequestURI())) {
            String openid = CookieUtil.get(request, CookieConstant.OPENID);
            if (openid == null) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            }
        }
        return null;
    }
}
