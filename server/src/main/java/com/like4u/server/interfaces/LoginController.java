package com.like4u.server.interfaces;

import com.like4u.server.domain.inet.service.LoginService;
import com.like4u.server.domain.inet.service.SecretKeyService;
import com.like4u.server.infrastructrue.comon.AjaxResult;
import com.like4u.server.infrastructrue.po.LoginDto;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/4/8 21:49
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private SecretKeyService secretKeyService;


    @PostMapping("/login")
    public AjaxResult Login(@RequestBody @Validated LoginDto loginDto){
        String token=null;
        try {
            loginDto= loginService.loginCheck(loginDto);
            SecretKey secretKey = secretKeyService.getSecretKey();
            Map<String, String> claims = new HashMap<>();
            claims.put("username",loginDto.getUserNickName());


            log.info("userId,{}",loginDto.getUserId());
            log.info("password,{}",loginDto.getUserPassword());
            log.info("User name: " + loginDto.getUserNickName());
            log.info("userHead{}",loginDto.getUserHead());


            token=Jwts.builder().signWith(secretKey).setClaims(claims).compact();
            log.info("Token: " + token);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return AjaxResult.success("登录成功", token);
    }


}
