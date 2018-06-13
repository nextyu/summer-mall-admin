package com.nextyu.mall.web.interceptor;

import com.nextyu.mall.context.UserContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created on 2017-07-13 14:49
 *
 * @author nextyu
 */
public class UserInfoInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       /* String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        UserContext.set(new UserInfo(token));*/
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.remove();
    }
}
