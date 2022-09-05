package com.sean.webclient.service.impl;

import com.sean.webclient.req.LoginReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl {

    @Value("${comm.authBase}")
    private String commUrl;

    @Value("${comm.login}")
    private String loginUri;

    private String token;

    public String getToken() {
        if (this.token == null) {
            this.login();
        } else {
            if(this.isExpired(this.token)) {
                this.refreshToken();
            }
        }

//        if (this.token == null || this.isExpired(this.token)) {
//            this.refreshToken();
//        }
        return this.token;
    }

    private void login() {
//        HashMap<String, String> result = WebClient.create().post()
//                .uri(commUrl + loginUri)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(getCredentials()))
//                .retrieve()
//                .bodyToMono(HashMap.class)
//                .block();
//        this.token = result.get("access_token");
        HashMap<String, String> result = WebClient.create().post()
                .uri(commUrl + loginUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(getCredentials()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<HashMap<String, String>>() {})
                .block();
        this.token = result.get("access_token");
    }

    private void refreshToken() {
        HashMap<String, String> result = WebClient.create().post()
                .uri(commUrl + loginUri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(getCredentials()))
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();
        this.token = null;
        System.out.println(result.get("access_token"));
    }

    private boolean isExpired(String token) {
        return false;
    }

    private Map<String, String> getCredentials() {
        Map<String, String> body= new HashMap<>();
        body.put("email", "happylim99@gmail.com");
        body.put("passwd", "abcd");
        return body;
    }
}
