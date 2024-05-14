package com.example.fanluprojects2.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.fanluprojects2.entity.person;
import com.example.fanluprojects2.exception.CustomException;
import com.example.fanluprojects2.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@CrossOrigin
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    LoginService loginService;

    public static Integer personId;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        log.info("token: {}",token);
        if(StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }
        if(StrUtil.isBlank(token)) {
            throw new CustomException("验证失败,请重新登陆");
        }
        person person;
        try {
            personId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
            person = loginService.findById(personId);
        } catch (JWTDecodeException e) {
            throw new CustomException("身份验证失败,请重新登陆");
        }
        log.info("尝试登陆用户{} id: {}",person,personId);
        if(person == null) {
            throw new CustomException("用户不存在,请重新登陆");
        }

        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(person.getPassword())).build();
            jwtVerifier.verify(token);
        } catch (IllegalArgumentException e) {
            throw new CustomException("身份验证失败,请重新登陆");
        }
        return true;
    }
}
