package com.sean.webclient.controller;

import com.sean.webclient.service.OkHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/ok")
public class OkHttpController {

    private final OkHttpService srv;

    @Autowired
    public OkHttpController(OkHttpService srv) {
        this.srv = srv;
    }

    @GetMapping("/login")
    public HashMap<String, String> login() throws Throwable {
        return srv.login();
    }
}
