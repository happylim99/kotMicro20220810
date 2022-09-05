package com.sean.webclient.service;

import com.sean.webclient.dto.UserDto;
import com.sean.webclient.req.LoginReq;

import java.util.HashMap;
import java.util.List;

public interface CommService {
    public HashMap<String, String> loginAuthWs(LoginReq loginReq);
    public List<UserDto> showAll();
}
