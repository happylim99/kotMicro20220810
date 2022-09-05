package com.sean.webclient.req;

public class LoginReq {

    private String email;
    private String passwd;

    public LoginReq(String email, String passwd) {
        this.email = email;
        this.passwd = passwd;
    }

    public LoginReq() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}