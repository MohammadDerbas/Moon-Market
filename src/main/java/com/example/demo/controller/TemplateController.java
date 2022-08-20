package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class TemplateController {
    @GetMapping("login")
    public String getLogin() {
        return "login";
    }
    @GetMapping("hello")
    public String getCourses() {
        return "hello";
    }
    @GetMapping("upload")
    public String getImg() {
        return "img";
    }
}
