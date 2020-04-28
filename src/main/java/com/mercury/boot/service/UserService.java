package com.mercury.boot.service;

import com.mercury.boot.bean.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userService")
public class UserService implements UserDetailsService {
    /**
     * 登陆验证时，通过username获取用户的所有权限信息
     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //在这里可以自己调用数据库，对username进行查询，看看在数据库中是否存在
        User user = new User();
        // 管理员判断
        if (username.equals("admin")) {
            user.setUsername(username);
            user.setPassword("admin");
        } else {
            user.setUsername(username);
            user.setPassword("123456");
        }
        return user;
    }
}
