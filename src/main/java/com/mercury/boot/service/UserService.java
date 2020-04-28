package com.mercury.boot.service;

import com.mercury.boot.bean.User;
import com.mercury.boot.config.CustomPasswordEncoder;
import com.mercury.boot.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//@Component("userService")
//public class UserService implements UserDetailsService {
//    /**
//     * 登陆验证时，通过username获取用户的所有权限信息
//     * 并返回UserDetails放到spring的全局缓存SecurityContextHolder中，以供授权器使用
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //在这里可以自己调用数据库，对username进行查询，看看在数据库中是否存在
//        User user = new User();
//        // 管理员判断
//        if (username.equals("admin")) {
//            user.setUsername(username);
//            user.setPassword("admin");
//        } else {
//            user.setUsername(username);
//            // 数据库查询
//            String sql = "select id from user where name = ?";
//            List<Map<String, Object>> result = new JdbcTemplate().queryForList(sql, username);
//            System.out.println(username);
//            if (result.isEmpty()) {
//                throw new UsernameNotFoundException("no user named " + username);
//            } else {
//                user.setPassword(result.get(0).get("password").toString());
//            }
//        }
//        return user;
//    }
//}

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    CustomPasswordEncoder customPasswordEncoder;

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public boolean addOneUser(User user){
        try {
            User existing = userDao.findByUsername(user.getUsername());
            if(existing == null){
                user.setPassword(customPasswordEncoder.encode(user.getPassword()));
                userDao.save(user);
                return true;
            } else {
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }
}
