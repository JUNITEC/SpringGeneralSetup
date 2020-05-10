package com.example.DefaultMicroAuth.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {

    /* testing end point */
    @GetMapping("login/hello")
    public String hello() {
        return "hello world";
    }

    @PutMapping("login/logout")
    public void logoutAccount(HttpServletResponse response){

    }

}