package com.liutianqi.webproject.helloworld.AuthProvider;

import com.liutianqi.webproject.helloworld.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthProviderController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callBack(@RequestParam("code") String code, HttpServletRequest httpServletRequest, Model model) {
        // 防止浏览器地址栏恶意输入callback?code=
        if (httpServletRequest.getSession().getAttribute("user") != null) {
            return "redirect:";
        }
        System.out.println("code:" + code);
        String accessToken = githubProvider.getAccessToken(code);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            httpServletRequest.getSession().setAttribute("user", githubUser);
            return "redirect:";
        }
        return "index";
    }
}
