package com.cdz.jn.controller;

import com.cdz.jn.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/login", "/login.html"})
    public String loginForm() {
        return "login_1";
    }

    @RequestMapping(value = {"/index", "/index.html"})
    public String index() {
        return "index_1";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        if (currentUser.isAuthenticated()) {
            return "redirect:index.html";
        } else {
            return "redirect:/login";
        }
    }
}
