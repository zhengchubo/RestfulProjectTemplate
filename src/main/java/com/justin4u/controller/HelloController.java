package com.justin4u.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("hello")
public class HelloController {

    @RequestMapping("/")
    public String say(HttpServletRequest request) {
        String name = request.getParameter("name");
        return "Hi, " + name;
    }
}
