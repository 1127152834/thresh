package com.zhang.thresh.gateway.enhance.controller;

import com.zhang.thresh.common.core.entity.ThreshResponse;
import com.zhang.thresh.gateway.enhance.auth.JwtTokenHelper;
import com.zhang.thresh.gateway.enhance.service.RouteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 登录
 * @author MrZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("route")
public class RouteLoginController {

    private final JwtTokenHelper tokenHelper;
    private final PasswordEncoder passwordEncoder;
    private final RouteUserService routeUserService;

    /**
     * 登录路由系统
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @GetMapping("login")
    public Mono<ResponseEntity<ThreshResponse>> login(String username, String password) {
        String error = "认证失败，用户名或密码错误";
        return routeUserService.findByUsername(username)
                .map(u -> passwordEncoder.matches(password, u.getPassword()) ?
                        ResponseEntity.ok(new ThreshResponse().data(tokenHelper.generateToken(u))) :
                        new ResponseEntity<>(new ThreshResponse().message(error), HttpStatus.INTERNAL_SERVER_ERROR))
                .defaultIfEmpty(new ResponseEntity<>(new ThreshResponse().message(error), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
