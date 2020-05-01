package com.mercury.boot.config;

import com.alibaba.fastjson.JSONObject;
import com.mercury.boot.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final CustomPasswordEncoder customPasswordEncoder;
    final UserService userService;

    public WebSecurityConfig(CustomPasswordEncoder customPasswordEncoder, UserService userService) {
        this.customPasswordEncoder = customPasswordEncoder;
        this.userService = userService;
    }

    @Override
    public void configure(WebSecurity web) {
        // 不拦截静态资源
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/lib/**");
        web.ignoring().antMatchers("/fonts/**");
        web.ignoring().antMatchers("/lang/**");
        web.ignoring().antMatchers("/static/**");

        // 不拦截的API
        web.ignoring().antMatchers("/api/login/*");
        web.ignoring().antMatchers("/api/logout/*");
        web.ignoring().antMatchers("/api/register/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())

                //定义哪些url需要保护，哪些url不需要保护
                .authorizeRequests().anyRequest().authenticated()

                .and()
                .sessionManagement().maximumSessions(1)
                .and()

                .and()
                .httpBasic()
                .authenticationEntryPoint((request, response, authException) -> {
                    JSONObject res = new JSONObject();
                    res.put("success", false);
                    res.put("msg", "need login");
                    response.setStatus(200);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().append(res.toString());
                })

                .and()
                .formLogin()
                .loginProcessingUrl("/api/login")
                .permitAll()
                .failureHandler((request, response, ex) -> {
                    JSONObject res = new JSONObject();
                    res.put("success", false);
                    res.put("msg", "Fail to login, Please check your username or password");
                    response.setStatus(200);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().append(res.toString());
                })
                .successHandler((request, response, authentication) -> {
                    JSONObject res = new JSONObject();
                    res.put("success", true);
                    res.put("msg", "login success");
                    response.setStatus(200);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().append(res.toString());
                })


                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    JSONObject res = new JSONObject();
                    res.put("success", true);
                    res.put("msg", "logout success");
                    response.setStatus(200);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().append(res.toString());
                })
                .permitAll()

                // 自动登录
                .and()
                .rememberMe();


        http.cors().disable();
        http.csrf().disable();

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(customPasswordEncoder);
        return authenticationProvider;
    }

}