package com.liutianqi.webproject.helloworld.AuthProvider;

import com.liutianqi.webproject.helloworld.dto.GithubUser;
import com.liutianqi.webproject.helloworld.dto.User;
import com.liutianqi.webproject.helloworld.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthProviderController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callBack(@RequestParam("code") String code, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        // 防止浏览器地址栏恶意输入callback?code=
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            return "redirect:/";
        }
        System.out.println("code:" + code);
        String accessToken = githubProvider.getAccessToken(code);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            // 登录成功，存入数据库
            User user = new User();
            user.setCode(githubUser.getId());
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString().substring(2, 10);
            user.setToken(token);
            // 存入数据库
            userMapper.insertUser(user);
            // 写入cookie
            httpServletResponse.addCookie(new Cookie("token", token));

//            httpServletRequest.getSession().setAttribute("user", githubUser);
            return "redirect:/hello";
        }
        return "index";
    }
}
