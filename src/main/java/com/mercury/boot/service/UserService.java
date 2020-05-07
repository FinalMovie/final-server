package com.mercury.boot.service;

import com.mercury.boot.bean.Users;
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

    public List<Users> getAllUsers() {
        return userDao.findAll();
    }

    public boolean saveUser(Users user) {
        try {
            user.setId(System.currentTimeMillis());
            userDao.save(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean userExist(Users user) {
        Users result = userDao.findByUsername(user.getUsername());
        return result != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = new Users();

        user.setUsername(username);
        Users result = userDao.findByUsername(username);
        if (result == null) {
            throw new UsernameNotFoundException("no user named " + username);
        } else {
            user.setPassword(result.getPassword());
        }

        return user;
    }

}
