package org.kaleval.springMVC.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello-world")
    public String sayHello() {
        return "/WEB-INF/hello_world.html";
    }
}