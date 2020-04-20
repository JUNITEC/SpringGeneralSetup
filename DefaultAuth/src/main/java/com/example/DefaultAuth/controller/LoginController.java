package com.example.DefaultAuth.controller;

import com.example.DefaultAuth.models.usertype.UserType;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {

    /* testing end point */
    @GetMapping("login/hello")
    public String hello() {
        return "hello world";
    }

    /* end point for authentication */
    @PostMapping("login/authenticate")
    public void createAuthToken(@RequestBody UserType user) {

    }

    @PutMapping("login/logout")
    public void logoutAccount(HttpServletResponse response){

    }

}