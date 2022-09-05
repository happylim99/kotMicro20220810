package com.sean.webclient.controller;

import com.sean.webclient.dto.UserDto;
import com.sean.webclient.req.LoginReq;
import com.sean.webclient.service.CommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/comm")
public class CommController {

    @Autowired
    private CommService srv;

    @GetMapping("/login")
    public HashMap<String, String> login(@RequestBody LoginReq loginReq) {
        return srv.loginAuthWs(loginReq);
    }

    @GetMapping("/showAll")
    public List<UserDto> showAll() {
        return srv.showAll();
    }
}
