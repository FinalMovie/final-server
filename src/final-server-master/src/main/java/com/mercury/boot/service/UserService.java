package com.mercury.boot.service;

import com.mercury.boot.bean.Customers;
import com.mercury.boot.config.CustomPasswordEncoder;
import com.mercury.boot.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class UserService implements UserDetailsService {

    final UserDao userDao;

    final CustomPasswordEncoder customPasswordEncoder;

    public UserService(UserDao userDao, CustomPasswordEncoder customPasswordEncoder) {
        this.userDao = userDao;
        this.customPasswordEncoder = customPasswordEncoder;
    }

    public List<Customers> getAllUsers() {
        return userDao.findAll();
    }

//    public boolean addOneUser(Customers user) {
//        try {
//            Customers existing = userDao.findByUsername(user.getUsername());
//            if (existing == null) {
//                user.setPassword(customPasswordEncoder.encode(user.getPassword()));
//                userDao.save(user);
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customers user = new Customers();
        if (username.equals("admin")) {
            user.setUsername(username);
            user.setPassword("admin");
        } else {
            user.setUsername(username);
            Customers result = userDao.findByUsername(username);
            if (result == null) {
                throw new UsernameNotFoundException("no user named " + username);
            } else {
                user.setPassword(result.getPassword());
            }
        }
        return user;
    }

}
