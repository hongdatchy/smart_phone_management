package com.hongdatchy.security;

import com.hongdatchy.common.AppConfig;

import java.util.Date;

public class TokenService {

    private EncryptDecryptStringWithDES encryptDecryptStringWithDES = new EncryptDecryptStringWithDES();

    public String getToken(String username){
        return  encryptDecryptStringWithDES.encrypt(AppConfig.PREFIX + username + "time" + new Date().getTime()) ;
    }

    public String getUsername(String token){
        String s = encryptDecryptStringWithDES.decrypt(token);

        s = s.replaceFirst(AppConfig.PREFIX, "");

        long initTokenTime = Long.parseLong(s.split("time")[s.split("time").length-1]);
        s = s.substring(0, s.lastIndexOf("time"));
        return new Date().getTime() - initTokenTime > AppConfig.expiredTime ? null : s;
    }

}
