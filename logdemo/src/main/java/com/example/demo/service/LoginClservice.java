package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.*;
import com.example.demo.dao.*;

@Service
public class LoginClservice {

    private final Userdao userdao;  //与dao层进行交互

    @Autowired
    public LoginClservice(Userdao userdao) {
        this.userdao = userdao;
    }

    public boolean Find(String username, String password) {
//        boolean b = true;
        userBean b = userdao.findByUsernameAndPassword(username, password);
        if (b == null) {
            return false;
        }
        String user = b.getUsername();
        String pwd = b.getPassword();
        System.out.println("-----------" + user + "and" + pwd);
        if (user.equals(username) && pwd.equals(password)) {
            System.out.println(9);
            return true;
        } else {
            System.out.println(0);
            return false;
        }
    }
}