package com.zhang.thresh.auth.configure;

import com.zhang.thresh.auth.filter.ValidateCodeFilter;
import com.zhang.thresh.auth.handler.ThreshWebLoginFailureHandler;
import com.zhang.thresh.auth.handler.ThreshWebLoginSuccessHandler;
import com.zhang.thresh.common.core.entity.constant.EndpointConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurity配置
 *
 * @author MrZhang
 */
@Order(2)
@EnableWebSecurity
@RequiredArgsConstructor
public class ThreshSecurityConfigure extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailService;
    private final ValidateCodeFilter validateCodeFilter;
    private final PasswordEncoder passwordEncoder;
    private final ThreshWebLoginSuccessHandler successHandler;
    private final ThreshWebLoginFailureHandler failureHandler;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                .antMatchers(EndpointConstant.OAUTH_ALL, EndpointConstant.LOGIN)
                .and()
                .authorizeRequests()
                .antMatchers(EndpointConstant.OAUTH_ALL).authenticated()
                .and()
                .formLogin()
                .loginPage(EndpointConstant.LOGIN)
                .loginProcessingUrl(EndpointConstant.LOGIN)
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()
                .and().csrf().disable()
                .httpBasic().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }
}
