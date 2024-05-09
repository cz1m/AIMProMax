package com.like4u.server.interceptor;
import com.like4u.server.config.InterceptorMode;
import com.like4u.server.domain.inet.service.SecretKeyService;
import com.like4u.server.exception.Exception401;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/9 22:30
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private SecretKeyService secretKeyService;

    @Value("${interceptor.mode}")
    private final InterceptorMode interceptorMode = InterceptorMode.NONE;

    @Value("${interceptor.whiteList}")
    private String[] interceptorWhiteList;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ("OPTIONS".equals(request.getMethod()) ||
                Stream.of(interceptorWhiteList).anyMatch(p -> p.equals(request.getRequestURI()))) {
            return true;
        }
        switch (interceptorMode) {
            case JWT -> {
                String token = request.getHeader("Authorization");
                System.out.println(Arrays.toString(interceptorWhiteList));
                if (token == null) {
                    throw new Exception401("未携带 token");
                }
                try {
                    SecretKey secretKey = secretKeyService.getSecretKey();
                    Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
                } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                         IllegalArgumentException e) {
                    throw new Exception401("校验 token 失败");
                }
            }
            case SESSION -> {
                Object user = request.getSession().getAttribute("user");
                if (user == null) {
                    throw new Exception401("校验 session 失败");
                }
            }
            case NONE -> {
            }
        }
        return true;
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
