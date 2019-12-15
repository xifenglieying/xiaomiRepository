package com.liutianqi.webproject.helloworld.AuthProvider;

import com.alibaba.fastjson.JSON;
import com.liutianqi.webproject.helloworld.dto.AccessToken;
import com.liutianqi.webproject.helloworld.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class GithubProvider {

    private static final String url = "https://github.com/login/oauth/access_token";

    public String getAccessToken(final String code) {
        AccessToken accessToken = new AccessToken();
        accessToken.setClient_id("ef6a9d39921d77bd3961");
        accessToken.setClient_secret("af30c906ca22314662bb3ba32d7f25ab3fdad902");
        accessToken.setRedirect_uri("http://localhost:8087/callback");
        accessToken.setCode(code);

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessToken));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(final String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?" + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            System.out.println(githubUser.toString());
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
