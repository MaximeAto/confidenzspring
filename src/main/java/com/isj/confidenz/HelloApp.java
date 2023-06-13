package com.isj.confidenz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloApp {

    @GetMapping("/")
    @ResponseBody
    public String hello(){
        return "good job";
    }
}
