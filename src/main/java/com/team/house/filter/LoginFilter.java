package com.team.house.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        StringBuffer requestURL = request.getRequestURL();
        String path = requestURL.substring(requestURL.lastIndexOf("/") + 1);
        if (path.equals("login.jsp")||path.equals("register")||path.equals("userConfirm")||path.equals("regs.jsp")||path.equals("login")){
            chain.doFilter(req, resp);
        }else{
            HttpSession session = request.getSession();
            Object loginInfo = session.getAttribute("loginInfo");
            if (loginInfo==null){
                response.sendRedirect("login.jsp");
            }else {
                chain.doFilter(req, resp);
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
