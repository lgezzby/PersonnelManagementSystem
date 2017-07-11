package com.zjgsu.interceptor;

import com.zjgsu.entity.User;
import com.zjgsu.util.common.HrmConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 判断用户权限的Spring MVC拦截器
 * Created by zby on 2017/7/11.
 */
public class AuthorizedInterceptor implements HandlerInterceptor {
    private static final String[] IGNORE_URI = {"/loginForm","/login","/404.html"};

    /**
     * 当preHandle的返回值是false的时候整个请求就结束了
     * 如果preHandle的返回值是true,则会继续执行postHandle和afterCompletion
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag = false;
        String servletPath = httpServletRequest.getServletPath();
        for (String s : IGNORE_URI){
            if (servletPath.contains(s)){
                flag = true;
                break;
            }
        }

        if (!flag){
            /* 获取session中的用户 */
            User user = (User) httpServletRequest.getSession().getAttribute(HrmConstants.USER_SESSION);

            if (user == null){
                httpServletRequest.setAttribute("message","请先登录再访问网站");
                httpServletRequest.getRequestDispatcher(HrmConstants.LOGIN).forward(httpServletRequest,httpServletResponse);
                return flag;
            }else {
                flag = true;
            }
        }
        return flag;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
