package com.liutianqi.webproject.helloworld.controller;

import com.liutianqi.webproject.helloworld.dto.User;
import com.liutianqi.webproject.helloworld.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/hello")
    public String helloWorld(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "count", required = false) String time, Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("name", name);
        model.addAttribute("count", time);
        User user = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
               user = userMapper.getUserByToken(token);
                break;
            }
        }
        if(user == null){
            return "index";
        } else {
            httpServletRequest.getSession().setAttribute("user", user);
            return "publish";
        }

    }
}
