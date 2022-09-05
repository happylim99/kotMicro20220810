package com.sean.webclient.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sean.webclient.dto.UserDto;
import com.sean.webclient.req.LoginReq;
import com.sean.webclient.service.CommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@Service
public class CommServiceImpl implements CommService {

    private final WebClient authWebClient;

    @Autowired
    public CommServiceImpl(
            @Qualifier("authWebClient") WebClient authWebClient
    ) {
        this.authWebClient = authWebClient;
    }

    @Value("${comm.authBase}")
    private String commUrl;

    @Value("${comm.login}")
    private String loginUri;

    @Value("${comm.showAll}")
    private String showAllUri;

    @Override
    public HashMap<String, String> loginAuthWs(LoginReq loginReq) {
        WebClient client = WebClient.create();

        LinkedMultiValueMap<String, String> credentials = new LinkedMultiValueMap<>();

        credentials.add("email", loginReq.getEmail());
        credentials.add("passwd", loginReq.getPasswd());

//        String response = client.post()
//                .uri(commUrl + loginUri)
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .accept(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromFormData(credentials))
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();

//        HashMap<String, String> response = client.post()
//                .uri(commUrl + loginUri)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .body(Mono.just(loginReq), LoginReq.class)
//                .retrieve()
//                .bodyToMono(HashMap.class)
//                .block();

        HashMap response = client.post()
                .uri(commUrl + loginUri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginReq), LoginReq.class)
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();

        return response;
    }

    @Override
    public List<UserDto> showAll() {
//        HashMap<String, String> token = loginAuthWs(new LoginReq("happylim99@gmail.com", "abcd"));
//        WebClient webClient = WebClient.create();
//        String response = authWebClient.get()
//                .uri(commUrl + showAllUri)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(String.class)
////                .map(jsonStr -> {
////                    ObjectMapper mapper = new ObjectMapper();
////                    try {
////                        JsonNode root = mapper.readTree(jsonStr);
////                        System.out.println(root.get(0));
////                    } catch (JsonProcessingException e) {
////                        e.printStackTrace();
////                    }
////                    return new UserDto();
////                });
//                .block();
//        System.out.println(response);

        List<UserDto> response = authWebClient.get()
                .uri(commUrl + showAllUri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(UserDto.class)
//                .bodyToMono(new ParameterizedTypeReference<List<UserDto>>() {})
//                .map(jsonStr -> {
//                    ObjectMapper mapper = new ObjectMapper();
//                    try {
//                        JsonNode root = mapper.readTree(jsonStr);
//                        System.out.println(root.get(0));
//                    } catch (JsonProcessingException e) {
//                        e.printStackTrace();
//                    }
//                    return new UserDto();
//                });
                .collectList().block();
        return response;
//        return null;
    }
}
