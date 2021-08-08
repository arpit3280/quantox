package com.quantox.security;

import com.quantox.service.LoginService;
import com.quantox.util.TokenVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HeaderInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(HeaderInterceptor.class);

    private final String signInUrl = "/authenticate/signIn";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();

        if ((requestUri.equals(signInUrl)))
            return true;
        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
        if( auth == null || auth.isEmpty()){
            response.sendError(401,"invalid user");
            return false;
        }
        TokenVerifier tokenVerifier = new TokenVerifier();
        long uid = tokenVerifier.verify(auth);
        request.setAttribute("uid",uid);

        return true;
    }
}
