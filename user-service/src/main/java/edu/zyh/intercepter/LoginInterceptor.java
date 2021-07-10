package edu.zyh.intercepter;

import edu.zyh.domain.Purchaser;
import edu.zyh.service.Impl.UserServiceImpl;
import edu.zyh.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("doFilter LoginFIlter");
        String token = request.getHeader("token");
        try {
            if (token == null || token.equals("")) {
                token = request.getParameter("token");
                if (token == null || token.equals("")) {
                    return false;
                }
            }

            Claims claims = JWTUtils.checkJWT(token);
            if (claims == null) {
                return false;
            } else {
                int id = (Integer) claims.get("id");
                String username = (String) claims.get("username");
                request.setAttribute("userId", id);
                request.setAttribute("username", username);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
