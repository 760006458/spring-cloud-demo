package com.example.controller;

import com.example.constant.CookieConstant;
import com.example.entity.User;
import com.example.enums.ResultEnum;
import com.example.enums.RoleEnum;
import com.example.repository.UserRepository;
import com.example.result.ResultVO;
import com.example.util.CookieUtil;
import com.example.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用于测试网关的权限校验功能
 *
 * @author xuan
 * @create 2018-06-07 13:37
 **/
@RestController
@RequestMapping("/login")
public class UserLoginController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private StringRedisTemplate template;

    /**
     * 买家登录，基于微信点餐项目，并不是使用username和password验证登录，
     * 而是使用openid（微信提供的用户唯一标示）
     *
     * @param openid
     * @return
     */
    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam String openid, HttpServletResponse response) {
        User user = repository.findByOpenid(openid);
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        if (RoleEnum.BUYER.getCode() != Integer.valueOf(user.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        //将openid存入cookie
        CookieUtil.setCookie(response, CookieConstant.OPENID, user.getOpenid(), CookieConstant.expire);
        return ResultVOUtil.success();
    }

    /**
     * 卖家登录
     *
     * @param openid 访问参数
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/seller")
    public ResultVO seller(@RequestParam String openid, HttpServletRequest request, HttpServletResponse response) {
        //已经登录过了：判断cookie中是否有"token":uuid
        String cookieValue = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookieValue != null && openid != null && openid.equals(template.opsForValue().get(cookieValue))) {  //根据UUID去Redis查询
            return ResultVOUtil.success();  //说明登录成功了
        }

        //第一次登录
        User user = repository.findByOpenid(openid);
        if (user == null) {
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }
        if (RoleEnum.SELLER.getCode() != Integer.valueOf(user.getRole())) {
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        //生成UUID存入Redis，格式uuid:openid
        String uuid = UUID.randomUUID().toString();
        template.opsForValue().set(uuid, user.getOpenid(), CookieConstant.expire, TimeUnit.SECONDS);
        //存入cookie，格式："token":uuid
        CookieUtil.setCookie(response, CookieConstant.TOKEN, uuid, CookieConstant.expire);
        return ResultVOUtil.success();
    }
}
