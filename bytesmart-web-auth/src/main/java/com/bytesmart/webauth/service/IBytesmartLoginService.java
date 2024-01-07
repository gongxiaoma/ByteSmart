package com.bytesmart.webauth.service;

public interface IBytesmartLoginService {

    public String login(String username, String password, String code, String uuid);

}
