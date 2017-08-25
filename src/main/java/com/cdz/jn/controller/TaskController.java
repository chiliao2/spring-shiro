package com.cdz.jn.controller;

import com.cdz.jn.entity.User;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @RequiresRoles("ADMIN")
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public Object getTasks() {
        User user = new User();
        user.setUsername("小玲子");
        user.setPassword("123445");
        user.setName("hhhh");
        return user;
    }
}
