package com.liutianqi.webproject.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "count", required = false) String time, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("count", time);
        return "index";
    }
}
