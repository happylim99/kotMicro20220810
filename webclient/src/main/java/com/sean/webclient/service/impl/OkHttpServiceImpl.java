package com.sean.webclient.service.impl;

import com.google.gson.Gson;
import com.sean.webclient.service.OkHttpService;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

@Service
public class OkHttpServiceImpl implements OkHttpService {

    private final OkHttpClient okHttpClient;
    private final HttpUrl.Builder authUrlBuilder;
    private final Gson gson;

    @Autowired
    public OkHttpServiceImpl(
            OkHttpClient okHttpClient,
            @Qualifier("authBaseUrl") HttpUrl.Builder authUrlBuilder,
            Gson gson
    ) {
        this.okHttpClient = okHttpClient;
        this.authUrlBuilder = authUrlBuilder;
        this.gson = gson;
    }

    public HashMap<String, String> login() throws Throwable {
//        RequestBody formBody = new FormBody.Builder()
//                .add("email", "happylim99@gmail.com")
//                .add("passwd", "abcd")
//                .build();
        // json formatted data
        String json = new StringBuilder()
                .append("{")
                .append("\"email\":\"happylim99@gmail.com\",")
                .append("\"passwd\":\"abcd\"")
                .append("}").toString();

        // json request body
        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json; charset=utf-8")
        );
        URL url = authUrlBuilder
                .addPathSegments("user/login")
                .build()
                .url();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            HashMap<String, String> res = gson.fromJson(response.body().string(), HashMap.class);
            return res;
        } catch (IOException e) {
            throw e;
        }


//        URL url = new HttpUrl.Builder()
//                .scheme("http")
//                .host("your_domain")
//                .addPathSegments("your_path")
//                .addQueryParameter("param1", "value1")
//                .build()
//                .url();

    }
}
