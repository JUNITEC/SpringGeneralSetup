package com.example.SimpleService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * This controller is only used for spring cloud testing purposes
 */
@Controller
public class SimpleController{


    @GetMapping("helloworld")
    public String helloWorldExample(){
        return "Hello I am a service";
    }
}