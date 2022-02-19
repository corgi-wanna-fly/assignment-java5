package io.spring.interceptor;

import io.spring.entity.Admin;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Component
public class RoleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        Admin admin = (Admin) session.getAttribute("admin");

        if(admin == null){
            response.sendRedirect("/admin/signin");

            return false;
        }
        return true;
    }
}
