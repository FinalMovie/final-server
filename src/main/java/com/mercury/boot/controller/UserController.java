package com.mercury.boot.controller;


import com.mercury.boot.bean.Users;
import com.mercury.boot.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping("/api/userByEmail")
    @ResponseBody
    public Users userByEmail(HttpServletRequest request) {
        String email = request.getParameter("email");
        return userService.findUserByEmail(email);
    }

    @RequestMapping("/api/currentUser")
    @ResponseBody
    public UserDetails currentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return userService.findUserByUserName(((UserDetails) principal).getUsername());
//            return (UserDetails) principal;
        } else {
            return null;
        }
    }

    @RequestMapping("/api/register")
    @ResponseBody
    public Map<String, Object> currentUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Users u = new Users();
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        u.setRole("user");
        u.setMembership(0);
        Map<String, Object> map = new HashMap<>();
        if (userService.userExist(u)) {
            map.put("success", false);
            map.put("msg", "user exist!");
        } else if (userService.saveUser(u)) {
            map.put("success", true);
            map.put("data", u);
        } else {
            map.put("success", false);
            map.put("msg", "failed to register user");
        }
        return map;
    }
}
