package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.*;
import com.example.demo.service.*;

@Controller
@RequestMapping("/Lee/")  //接口
public class LoginController {
    //与service层进行交互
    private final LoginClservice loginClservice;

    @Autowired
    public LoginController(LoginClservice loginClservice) {
        this.loginClservice = loginClservice;
    }

    @RequestMapping("users")
    public String getLoginCl(@RequestParam("username") String username,@RequestParam("password") String password){
        boolean b;
        b=loginClservice.Find(username,password);  //调用service的方法
        System.out.println(username);
        System.out.println(password);

        if(b=true){
            return "welcome";
        }
        return "login";
    }
}
